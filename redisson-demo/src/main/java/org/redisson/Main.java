package org.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
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
        RLock fairLock = redissonClient.getFairLock("key2");
        fairLock.lock();
        fairLock.unlock();
        RedissonMultiLock multiLock = new RedissonMultiLock(fairLock, lock);

// traditional lock method
        multiLock.lock();

// or acquire lock and automatically unlock it after 10 seconds
        multiLock.lock(10, TimeUnit.SECONDS);
//        RedissonClient redissonClient = Redisson.create();
//        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("key1");
//        RLock readLock = readWriteLock.readLock();
//        RLock writeLock = readWriteLock.writeLock();
//        readLock.lock();
//        writeLock.lock();
//        readLock.unlock();
//        writeLock.unlock();

    }
}