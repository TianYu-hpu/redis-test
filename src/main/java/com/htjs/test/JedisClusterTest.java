package com.htjs.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

public class JedisClusterTest {



    public static void main(String[] args) {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7001));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7002));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7003));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7004));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7005));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7006));

        // 设置连接池配置
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);  // 最大连接数
        poolConfig.setMaxIdle(20);   // 最大空闲连接数
        poolConfig.setMinIdle(5);    // 最小空闲连接数

        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, poolConfig);
        long begin = System.currentTimeMillis();
        for(int i = 0; i < 100; i++) {
            jedisCluster.set(String.valueOf(i), String.valueOf(i));
        }

        long middle = System.currentTimeMillis();
        System.out.println("存储10万个键值对耗费时间:" + (middle - begin) + " ms");
        for(int j = 0; j < 100; j++) {
            jedisCluster.get(String.valueOf(j));
        }

        long end = System.currentTimeMillis();
        System.out.println("查询10万个键值对共耗时:" + (end - middle) + " ms");
    }
}
