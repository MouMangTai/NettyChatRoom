package com.moumangtai.demo.protocol;


import com.moumangtai.demo.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


/**
 * 编解码的协议规定
 */
@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {


    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        //按照自定义的格式将msg写入创建好的ByteBuf中就行
        // 1. 4个字节的魔数
        out.writeBytes(new byte[]{8,2,8,2});
        // 2. 1个字节的版本号
        out.writeByte(1);
        // 3. 1个字节的序列化算法的类型
        out.writeByte(0);
        // 4. 1个字节的指令类型
        out.writeByte(msg.getMessageType());
        // 5. 4个字节的请求序列
        out.writeInt(msg.getSequenceId());
        // 6. 填充无意义字节以满足2的整数倍
        out.writeByte(0);
        // 获取正文的字节数组（采用JDK编码）
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();
        // 7. 4个字节的正文长度
        out.writeInt(bytes.length);
        // 8. 正文
        out.writeBytes(bytes);

    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 按照自定义的格式取出
        // 1. 4个字节的魔数
        int magicNum = in.readInt();
        // 2. 1个字节的版本号
        byte version = in.readByte();
        // 3. 1个字节的序列化算法的类型
        byte serializeMethod = in.readByte();
        // 4. 1个字节的指令类型
        byte messageType = in.readByte();
        // 5. 4个字节的请求序列
        int requenceId = in.readInt();
        // 6. 填充无意义字节以满足2的整数倍
        in.readByte();
        // 7. 4个字节的正文长度
        int len = in.readInt();
        // 获取正文的字节数组（采用JDK编码）
        byte[] bytes = new byte[len];
        in.readBytes(bytes, 0, len);
        // 8. 正文
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message)ois.readObject();
        out.add(message);
    }
}
