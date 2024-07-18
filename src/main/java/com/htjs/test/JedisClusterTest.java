package com.htjs.test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class JedisClusterTest {



    public static void main(String[] args) throws IOException {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7001));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7002));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7003));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7004));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7005));
        jedisClusterNodes.add(new HostAndPort("192.168.220.132", 7006));
        JedisPoolConfig pool = new JedisPoolConfig();
        pool.setMaxTotal(128); // 设置最大连接数
        pool.setMaxIdle(128);  // 设置最大空闲连接数
        pool.setMinIdle(16);   // 设置最小空闲连接数
        pool.setTestOnBorrow(true); // 借出连接时进行有效性检查
        pool.setTestOnReturn(true); // 归还连接时进行有效性检查
        pool.setMaxWaitMillis(10000); // 设置最大等待毫秒数
        long begin = System.currentTimeMillis();
        for(int i = 0; i < 100000; i++) {
            JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes, pool);
            jedisCluster.set("random" + i, String.valueOf(new Random().nextInt()));
            jedisCluster.close();
            System.out.println(i);
        }

        long middle = System.currentTimeMillis();
        System.out.println("存储10万个键值对耗费时间:" + (middle - begin) + " ms");
        for(int j = 0; j < 100000; j++) {
            JedisCluster jedisCluster2 = new JedisCluster(jedisClusterNodes, pool);
            jedisCluster2.get("random" + j);
            jedisCluster2.close();
        }

        long end = System.currentTimeMillis();
        System.out.println("查询10万个键值对共耗时:" + (end - middle) + " ms");
    }
}
