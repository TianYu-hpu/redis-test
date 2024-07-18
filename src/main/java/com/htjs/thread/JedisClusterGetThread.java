package com.htjs.thread;

import redis.clients.jedis.JedisCluster;

import java.util.concurrent.CountDownLatch;

public class JedisClusterGetThread implements Runnable{

    private JedisCluster jedisCluster;
    private String key;
    private CountDownLatch countDownLatch;

    public JedisClusterGetThread(JedisCluster jedisCluster, String key, CountDownLatch countDownLatch) {
        this.jedisCluster = jedisCluster;
        this.key = key;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        jedisCluster.get(key);
        countDownLatch.countDown();
    }
}
