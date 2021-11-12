package com.moumangtai.demo.constant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 有关消息的常量
 */
public class MessageConstant {

    /**
     * 用于同步处理
     */
    public static final CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);
    public static final AtomicBoolean IS_LOGIN = new AtomicBoolean(false);
    /**
     * 请求类型常量
     */
    public static final int LOGIN_REQUEST_MESSAGE = 0;
    public static final int LOGIN_RESPONSE_MESSAGE = 1;
    public static final int SEND_REQUEST_MESSAGE = 2;
    public static final int SEND_RESPONSE_MESSAGE = 3;
    public static final int GCREATE_REQUEST_MESSAGE = 4;
    public static final int GCREATE_RESPONSE_MESSAGE = 5;
    public static final int GSEND_REQUEST_MESSAGE = 6;
    public static final int GSEND_RESPONSE_MESSAGE = 7;

}
