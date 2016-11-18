package com.roc.core.Utils.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: rocwu
 * Date: 2016/11/17
 * Time: 19:31
 * Desc: 缓存基类
 */
public abstract class BaseCache<K, V> {

    private final int capacity;
    private final Map<K, V> map;
    private final ReadWriteLock readWriteLock;

    public BaseCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        readWriteLock = new ReentrantReadWriteLock();
    }

    /**
     * override by subclass
     * @param key key
     * @return value from non-cache (e.g. db)
     */
    public V getDirectly(K key) {
        return null;
    }

    public final V get(K key) {
        readWriteLock.readLock().lock();
        V value = null;
        try {
            value = map.get(key);
            if (value == null) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    value = map.get(key);
                    if (value == null) {
                        value = getDirectly(key);
                        if (value!=null) map.put(key, value);
                    }
                    readWriteLock.readLock().lock();
                } finally {
                    readWriteLock.writeLock().unlock();
                }
            }

        } finally {
            readWriteLock.readLock().unlock();
        }
        return value;
    }

    public final V set(K key, V value) {
        readWriteLock.writeLock().lock();
        try {
            while (map.size() >= capacity)
                map.remove(map.keySet().iterator().next());
            map.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
        return value;
    }

    public final V remove(K key) {
        readWriteLock.writeLock().lock();
        V removeValue = null;
        try {
            removeValue = map.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();
        }
        return removeValue;
    }
}