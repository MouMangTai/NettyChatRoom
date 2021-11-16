package com.moumangtai.demo.constant;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 序列id生成器
 *
 */
public class SequenceIdGenerator {

    private static final AtomicInteger sequenceId = new AtomicInteger(1);

    public static int getSequenceId(){
        return sequenceId.incrementAndGet();
    }
}
