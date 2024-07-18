package com.htjs.thread;

import redis.clients.jedis.Jedis;

import java.util.concurrent.CountDownLatch;

public class JedisSetThread implements Runnable{

    private Jedis jedis;
    private String key;
    private String value;
    private CountDownLatch countDownLatch;

    public JedisSetThread(Jedis jedis, String key, String value,  CountDownLatch countDownLatch) {
        this.jedis = jedis;
        this.key = key;
        this.value = value;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        jedis.set(key,  value);
        countDownLatch.countDown();
    }
}
