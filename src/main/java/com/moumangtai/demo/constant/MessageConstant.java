package com.moumangtai.demo.constant;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class MessageConstant {

    public static final CountDownLatch WAIT_FOR_LOGIN = new CountDownLatch(1);

    public static final AtomicBoolean IS_LOGIN = new AtomicBoolean(false);

    public static final int LOGIN_REQUEST_MESSAGE = 0;
    public static final int LOGIN_RESPONSE_MESSAGE = 1;


}
