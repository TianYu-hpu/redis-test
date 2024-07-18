package com.htjs.thread;

import redis.clients.jedis.Jedis;

import java.util.concurrent.CountDownLatch;

public class JedisGetThread implements Runnable{

    private Jedis jedis;
    private String key;
    private CountDownLatch countDownLatch;

    public JedisGetThread(Jedis jedis, String key, CountDownLatch countDownLatch) {
        this.jedis = jedis;
        this.key = key;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        jedis.get(key);
        countDownLatch.countDown();
    }
}
