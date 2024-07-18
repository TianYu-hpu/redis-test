package com.htjs.test;


import redis.clients.jedis.Jedis;

public class JedisTest {

    /**
     * 存储10万个键值对耗费时间:66421 ms
     * 查询10万个键值对共耗时:60242 ms
     * @param args
     */
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.220.132", 6379);

        long begin = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++) {
            jedis.set(String.valueOf(i), String.valueOf(i));
        }

        long middle = System.currentTimeMillis();
        System.out.println("存储10万个键值对耗费时间:" + (middle - begin) + " ms");
        for(int j = 0; j < 100000; j++) {
            jedis.get(String.valueOf(j));
        }

        long end = System.currentTimeMillis();
        System.out.println("查询10万个键值对共耗时:" + (end - middle) + " ms");
    }
}
