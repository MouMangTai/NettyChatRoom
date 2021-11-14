package com.moumangtai.demo.constant;

import com.moumangtai.demo.message.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 有关消息的常量
 */
public class MessageConstant {

    private final static Map<Byte,Class<?>> map = new HashMap<>();

    static {
        map.put(MessageConstant.LOGIN_REQUEST_MESSAGE, LoginRequestMessage.class);
        map.put(MessageConstant.LOGIN_RESPONSE_MESSAGE, LoginResponseMessage.class);
        map.put(MessageConstant.SEND_REQUEST_MESSAGE, SendRequestMessage.class);
        map.put(MessageConstant.SEND_RESPONSE_MESSAGE, SendResponseMessage.class);
        map.put(MessageConstant.GCREATE_REQUEST_MESSAGE, GcreateRequestMessage.class);
        map.put(MessageConstant.GCREATE_RESPONSE_MESSAGE, GcreateResponseMessage.class);
        map.put(MessageConstant.GSEND_REQUEST_MESSAGE, GsendRequestMessage.class);
        map.put(MessageConstant.GSEND_RESPONSE_MESSAGE, GsendResponseMessage.class);
        map.put(MessageConstant.REGISTER_REQUEST_MESSAGE, RegisterRequestMessage.class);
        map.put(MessageConstant.REGISTER_RESPONSE_MESSAGE, RegisterResponseMessage.class);
    }

    /**
     * 根据消息类型返回消息的Class
     * @param messageType
     * @return
     */
    public static Class<?> getMessageClass(byte messageType){
        return map.get(messageType);
    }

    /**
     * 用于同步处理
     */
    public static CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);
    public static CountDownLatch WAIT_FOR_REGISTER = new CountDownLatch(1);
    public static final AtomicBoolean IS_REGISTER = new AtomicBoolean(false);
    public static final AtomicBoolean IS_LOGIN = new AtomicBoolean(false);
    /**
     * 请求类型常量
     */
    public static final byte LOGIN_REQUEST_MESSAGE = 0;
    public static final byte LOGIN_RESPONSE_MESSAGE = 1;
    public static final byte SEND_REQUEST_MESSAGE = 2;
    public static final byte SEND_RESPONSE_MESSAGE = 3;
    public static final byte GCREATE_REQUEST_MESSAGE = 4;
    public static final byte GCREATE_RESPONSE_MESSAGE = 5;
    public static final byte GSEND_REQUEST_MESSAGE = 6;
    public static final byte GSEND_RESPONSE_MESSAGE = 7;
    public static final byte REGISTER_REQUEST_MESSAGE = 8;
    public static final byte REGISTER_RESPONSE_MESSAGE = 9;

}
