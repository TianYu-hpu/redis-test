package com.htjs.thread;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.concurrent.CountDownLatch;

public class JedisClusterSetThread implements Runnable{

    private JedisCluster jedisCluster;
    private String key;
    private String value;
    private CountDownLatch countDownLatch;

    public JedisClusterSetThread(JedisCluster jedisCluster, String key, String value,  CountDownLatch countDownLatch) {
        this.jedisCluster = jedisCluster;
        this.key = key;
        this.value = value;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        jedisCluster.set(key,  value);
        countDownLatch.countDown();
    }
}
