package com.moumangtai.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回给后端的http响应统一格式
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    /**
     * 响应是否成功，true 为成功，false 为失败
     */
    private Boolean success;

    /**
     * 响应码
     */
    private int code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private Map<String,Object> data = new HashMap<>();

    /**
     * 返回一个默认的错误信息
     * @return
     */
    public static Result error(){
        return new Result(false,HttpEnum.ERROR.getCode(), HttpEnum.ERROR.getMsg(),null);
    }

    /**
     * 返回无参的正确信息
     * @return
     */
    public static Result success(){
        return new Result(true,HttpEnum.SUCCESS.getCode(), HttpEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 返回有参的正确信息
     * @param data
     * @return
     */
    public static Result success(Map<String,Object> data){
        return new Result(true,HttpEnum.SUCCESS.getCode(), HttpEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 返回有参的正确信息
     * @param message
     * @param data
     * @return
     */
    public static Result success(String message,Map<String,Object> data){
        return new Result(true,HttpEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 返回有参的正确信息
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static Result success(Integer code,String message,Map<String,Object> data){
        return new Result(true,code , message, data);
    }
}
