package com.moumangtai.demo.util;


import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *编写工具类，这里的@Component注解不能丢，否则jedisPool为空，
 */
@Component
public class RedisUtil {
    @Autowired
    private JedisPool jedisPool;
    /**
     * 获取单个对象
     */
    public <T> T get(String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = jedis.get(key);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     *  得到对应键的字符串值
     * @param key
     * @return
     */
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        }  finally {
            returnToPool(jedis);
        }
    }

    /**
     * 选择从哪个redis库中，获取对象信息
     */
    public <T> T get(int dbNum, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbNum);
            String str = jedis.get(key);
            T t = stringToBean(str, clazz);
            return t;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 设置对象
     */
    public <T> boolean set(String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            jedis.set(key, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 选择redis库，并写入数据
     */
    public <T> boolean set(int dbNum, String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            jedis.select(dbNum);
            jedis.set(key, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 向缓存中写入值，并设置失效时间
     **/
    public <T> boolean setex(String key, T value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            jedis.setex(key, seconds, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 选择缓存库，向缓存中写入值，并设置失效时间
     **/
    public <T> boolean setex(int dbNum, String key, T value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            jedis.select(dbNum);
            jedis.setex(key, seconds, str);
            return true;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 判断key是否存在
     */
    public <T> boolean exists(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 删除
     */
    public boolean delete(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            long ret = jedis.del(key);
            return ret > 0;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 删除指定库中单值
     */
    public boolean delete(String key,int database) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(database);
            long ret = jedis.del(key);
            return ret > 0;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     *  删除一组数据
     */
    public boolean deletes(String[] keys) {
        boolean deletes = true;
        for (String key : keys) {
            boolean delete = delete(key);
            if (!delete) {
                deletes = false;
            }
        }
        return deletes;
    }


    /**
     * 修改数据
     */
    public boolean update(String key, String value) {
        Jedis jedis = null;
        if (jedis.exists(key)) {
            jedis.set(key, value);
            if (value.equals(jedis.get(key))) {
                System.out.println("修改数据成功");
                return true;
            } else {
                System.out.println("修改数据失败");
                return false;
            }
        } else {
            System.out.println(key + "不存在");
            System.out.println("若要新增数据请使用set()方法");
            return false;
        }
    }


    /**
     * 关闭jedis
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T stringToBean(String str, Class<T> clazz) {
        if (str == null || str.length() <= 0 || clazz == null) {
            return null;
        }
        if (clazz == int.class || clazz == Integer.class) {
            return (T) Integer.valueOf(str);
        } else if (clazz == String.class) {
            return (T) str;
        } else if (clazz == long.class || clazz == Long.class) {
            return (T) Long.valueOf(str);
        } else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }

    public static <T> String beanToString(T value) {
        if (value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if (clazz == int.class || clazz == Integer.class) {
            return "" + value;
        } else if (clazz == String.class) {
            return (String) value;
        } else if (clazz == long.class || clazz == Long.class) {
            return "" + value;
        } else {
            return JSON.toJSONString(value);
        }
    }

}
