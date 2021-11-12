package com.moumangtai.demo.factory;

import com.moumangtai.demo.session.GroupSession;

/**
 * 创建一个生成群会话的工厂
 */
public class GroupSessionFactory {

    private static GroupSession groupSession = new GroupSession();

    public static GroupSession getGroupSession(){
        return groupSession;
    }
}
