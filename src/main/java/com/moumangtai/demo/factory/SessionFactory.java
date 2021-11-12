package com.moumangtai.demo.factory;

import com.moumangtai.demo.session.Session;

public class SessionFactory {

    private static Session session = new Session();

    public static Session getSession(){
        return session;
    }
}
