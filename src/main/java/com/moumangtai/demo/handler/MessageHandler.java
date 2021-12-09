package com.moumangtai.demo.handler;

import com.google.gson.Gson;
import com.moumangtai.demo.constant.MessageEnum;
import com.moumangtai.demo.entity.AllChat;
import com.moumangtai.demo.entity.Chat;
import com.moumangtai.demo.entity.User;
import com.moumangtai.demo.factory.SessionFactory;
import com.moumangtai.demo.message.Message;
import com.moumangtai.demo.message.MessageContent;
import com.moumangtai.demo.service.IAllChatService;
import com.moumangtai.demo.service.IChatService;
import com.moumangtai.demo.service.IUserService;
import com.moumangtai.demo.service.impl.AllChatServiceImpl;
import com.moumangtai.demo.service.impl.ChatServiceImpl;
import com.moumangtai.demo.service.impl.UserServiceImpl;
import com.moumangtai.demo.util.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有客户端的channels
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "连接");
        channels.add(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "发生异常");

        SessionFactory.getSession().unbind(ctx.channel());
        channels.remove(ctx.channel());
        cause.printStackTrace();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "断开连接");
        Gson gson = new Gson();
        Message message = new Message();
        message.setMessageType(MessageEnum.DISCONNECT.code);
        message.setExtend(gson.toJson(SessionFactory.getSession().getUserId(ctx.channel())));
        channels.writeAndFlush(new TextWebSocketFrame(gson.toJson(message)));

        SessionFactory.getSession().unbind(ctx.channel());
        channels.remove(ctx.channel());

        System.out.println("发送信息："+gson.toJson(message).toString());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame frame) throws Exception {
        // 解析数据
        String text = frame.text();
        // 如果是心跳
        if(text.equals("ping")) {
            ctx.writeAndFlush(new TextWebSocketFrame("pong"));
            return;
        }
        Gson gson = new Gson();
        Message msg = gson.fromJson(text, Message.class);
        Integer messageType = msg.getMessageType();


        IChatService iChatService = SpringUtil.getBean(ChatServiceImpl.class);
        IUserService iUserService = SpringUtil.getBean(UserServiceImpl.class);
        IAllChatService iAllChatService = SpringUtil.getBean(AllChatServiceImpl.class);

        if (messageType.equals(MessageEnum.CONNECT.code)) {
            log.info("连接信息");
            String extend = msg.getExtend();
            User user = gson.fromJson(extend, User.class);
            // 将userId和channel关联起来
            SessionFactory.getSession().bind(user.getId(), ctx.channel());

            // 广播有人连接的消息
            channels.writeAndFlush(new TextWebSocketFrame(text));

            // 查询该用户是否有未接受的消息
            List<Chat> unSendChat = iChatService.getUnSendChatByUserId(user.getId());
            // 循环发送（后续需要改为一次批量发送）
            if (unSendChat != null && unSendChat.size() > 0) {
                log.info("发送离线消息");
                unSendChat.stream().forEach(chat -> {
                    // 根据chat构建消息体
                    Message m = ChatToMessage(chat);
                    ctx.channel().writeAndFlush(new TextWebSocketFrame(gson.toJson(m)));
                });
            }
        } else if(messageType.equals(MessageEnum.DISCONNECT.code)){
            log.info("断开连接信息");
            String extend = msg.getExtend();
            User user = gson.fromJson(extend, User.class);
            // 将userId和channel关联起来
            SessionFactory.getSession().unbind(user.getId());
            // 广播有人断开连接的消息
            channels.writeAndFlush(new TextWebSocketFrame(text));
        } else if (messageType.equals(MessageEnum.SEND.code)) {
            log.info("单发消息");
            // 保存聊天记录，状态为已发送
            MessageContent mc = msg.getMessageContent();
            Chat chat = new Chat(null, messageType, mc.getFromId(), mc.getToId(), mc.getContent(), 0, null);
            iChatService.save(chat);

            // 设置消息id用于后续ack应答
            msg.setMessageId(chat.getId());
            // 发送给接收方
            Channel recieveChannel = SessionFactory.getSession().getChannel(mc.getToId());
            if (recieveChannel == null) {
                log.info("map中没有该用户的channel");
                // 用户离线
            } else {
                Channel find = channels.find(recieveChannel.id());
                if (find == null) {
                    log.info("ChannelGroup中没有该用户的channel");
                    // 用户离线
                } else {
                    // 用户在线的情况下直接发送
                    recieveChannel.writeAndFlush(new TextWebSocketFrame(gson.toJson(msg)));
                }
            }
        } else if (messageType.equals(MessageEnum.ACK_SEND.code)) {
            log.info("签收信息");
            // 批量修改签收到的信息
            String extend = msg.getExtend();
            String[] splits = extend.split(",");
            List<Long> ids = Arrays.stream(splits).map(s -> Long.parseLong(s)).collect(Collectors.toList());
            iChatService.updateACKSendBatch(ids);
        } else if (messageType.equals(MessageEnum.GROUP_SEND.code)) {
            log.info("群发消息");

        } else if (messageType.equals(MessageEnum.ALL_SEND.code)) {
            log.info("发送的是广播消息");
            MessageContent mc = msg.getMessageContent();
            User fromUser = iUserService.getById(mc.getFromId());
            msg.setExtend(gson.toJson(fromUser));
            Collection<Channel> channels = SessionFactory.getSession().getIdToChannel().values();
            for (Channel channel : channels) {
                channel.writeAndFlush(new TextWebSocketFrame(gson.toJson(msg)));
            }

            // 存储聊天记录
            iAllChatService.save(new AllChat(null,fromUser.getId(),mc.getContent(),null));
        }
    }

    /**
     * 将chat转换为message
     *
     * @param chat
     * @return
     */
    private Message ChatToMessage(Chat chat) {
        // 构建消息内容体
        MessageContent mc = new MessageContent();
        mc.setContent(chat.getContent());
        mc.setFromId(chat.getFromId());
        mc.setToId(chat.getToId());

        // 构建消息体
        Message m = new Message();
        m.setMessageId(chat.getId());
        m.setMessageType(MessageEnum.SEND.code);
        m.setMessageContent(mc);
        m.setExtend(null);
        return m;
    }
}
