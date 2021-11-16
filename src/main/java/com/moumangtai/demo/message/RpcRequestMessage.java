package com.moumangtai.demo.message;

import com.moumangtai.demo.constant.MessageConstant;
import lombok.Data;

/**
 * RPC请求体
 */
@Data
public class RpcRequestMessage extends Message{

    //调用的接口全限定名
    private String className;

    //调用接口中的方法名
    private String methodName;

    //方法返回类型
    private Class<?> returnType;

    //方法参数类型数组
    private Class[] parameterTypes;

    //方法参数值数组
    private Object[] parameters;

    public RpcRequestMessage() {
        super();
    }

    public RpcRequestMessage(int sequenceId, String className, String methodName, Class<?> returnType, Class[] parameterTypes, Object[] parameters) {
        super(sequenceId);
        this.className = className;
        this.methodName = methodName;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
    }

    @Override
    public int getMessageType() {
        return MessageConstant.RPC_REQUEST_MESSAGE;
    }
}
