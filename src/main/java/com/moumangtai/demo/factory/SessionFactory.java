package com.moumangtai.demo.factory;

import com.moumangtai.demo.session.Session;

/**
 * 创建一个生成用户会话的工厂
 */
public class SessionFactory {

    private static Session session = new Session();

    public static Session getSession(){
        return session;
    }
}
