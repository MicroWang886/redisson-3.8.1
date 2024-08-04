package org.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception{
        RedissonClient redissonClient = Redisson.create();
        RLock lock = redissonClient.getLock("key1");
        lock.lock();
        System.out.println(1);
        Thread.sleep(1000);
        lock.unlock();
        lock.tryLock(100, 10, TimeUnit.SECONDS);
    }
}