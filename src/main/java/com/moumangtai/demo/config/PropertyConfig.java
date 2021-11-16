package com.moumangtai.demo.config;

import com.moumangtai.demo.protocol.Serializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件
 */
@Component
public class PropertyConfig {

    //默认为JDK
    private static String value = "FASTJSON";

    //通过Value注解读取配置文件，如果属性不是static可以直接加在属性上。注意这里的方法不能为static
    @Value("${serializer.type}")
    public void setValue(String value) {
        PropertyConfig.value = value;
    }

    /**
     * 根据配置文件来决定返回的序列化算法
     * @return
     */
    public static Serializer.Algorithm getSerialize(){
        return Serializer.Algorithm.valueOf(value);
    }
}
