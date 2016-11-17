package com.roc.core.Utils.cache;

import java.util.*;

/**
 * User: rocwu
 * Date: 2016/11/17
 * Time: 19:31
 * Desc: 缓存基类
 */
public abstract class BaseCache<T> {
    private CacheStrategy strategy = CacheStrategy.FIFO;
    private int capacity = 10000;
    public Map<CombineKey, T> cache;
    private Deque<CombineKey> fifoQueue;
    private TreeMap<Long, LinkedHashSet<CombineKey>> lruQueue;  // 最后访问时间=>key
    private TreeMap<Long, LinkedHashSet<CombineKey>> lfuQueue;  // 访问次数=>key
    private HashMap<CombineKey, Long> visitTime;
    private HashMap<CombineKey, Long> visitCount;

    public BaseCache() {
        init();
    }

    public BaseCache(CacheStrategy cacheStrategy, int capacity) {
        this.strategy = cacheStrategy;
        this.capacity = capacity;
        init();
    }

    private void init() {
        cache = new HashMap<>();
        switch (strategy) {
            case FIFO:
                fifoQueue = new ArrayDeque<>();
                break;
            case LRU:
                lruQueue = new TreeMap<>();
                visitTime = new HashMap<>();
                break;
            case LFU:
                lfuQueue = new TreeMap<>();
                visitCount = new HashMap<>();
                break;
        }
    }

    public T get(Object... keys) {
        CombineKey combineKey = new CombineKey(keys);
        T result = cache.get(combineKey);
        if (result != null){
            updateCacheQueueAfterHint(keys);
        } else {
            result = getDirectly(keys);
            if (result != null) {
                set(result, keys);
            }
        }
        return null;
    }

    public void set(T value, Object... keys) {
        CombineKey combineKey = new CombineKey(keys);
        if (cache.containsKey(combineKey))
            updateCacheQueueAfterRomove(keys);
        cache.put(combineKey, value);
        if (cache.size() > capacity) {
            CombineKey deleteKey = selectDeleteKey();
            updateCacheQueueAfterRomove(deleteKey.keys);
        }
        updateCacheQueueAfterAdd(keys);
    }

    private CombineKey selectDeleteKey() {
        CombineKey deleteKey = null;
        switch (strategy) {
            case FIFO:
                while (!fifoQueue.isEmpty()) {
                    deleteKey = fifoQueue.pollFirst();
                    if (cache.containsKey(deleteKey)) {
                        cache.remove(deleteKey);
                        break;
                    }
                }
                break;
            case LRU: {
                Map.Entry<Long, LinkedHashSet<CombineKey>> entry = lruQueue.firstEntry();
                LinkedHashSet<CombineKey> set = entry.getValue();
                Iterator<CombineKey> iterator = set.iterator();
                deleteKey = iterator.next();
            }
                break;
            case LFU: {
                Map.Entry<Long, LinkedHashSet<CombineKey>> entry = lfuQueue.firstEntry();
                LinkedHashSet<CombineKey> set = entry.getValue();
                Iterator<CombineKey> iterator = set.iterator();
                deleteKey = iterator.next();
            }
                break;
        }
        return deleteKey;
    }

    public T getDirectly(Object... keys) {
        return null;
    }

    /**
     * 命中缓存后更新缓存队列
     * @param keys key
     */
    private void updateCacheQueueAfterHint(Object... keys) {
        switch (strategy) {
            case FIFO:
                break;
            case LRU:
                {
                    long currentTime = System.currentTimeMillis();
                    CombineKey combineKey = new CombineKey(keys);
                    long preTime = visitTime.get(combineKey);
                    LinkedHashSet<CombineKey> set = lruQueue.get(preTime);
                    set.remove(combineKey);
                    if (set.isEmpty()) lruQueue.remove(preTime);
                    set = lruQueue.get(currentTime);
                    if (set == null) {
                        set = new LinkedHashSet<>();
                        lruQueue.put(currentTime, set);
                    }
                    set.add(combineKey);
                    visitTime.put(combineKey, currentTime);
                }
                break;
            case LFU:
                {
                    CombineKey combineKey = new CombineKey(keys);
                    long preCount = visitCount.get(combineKey);
                    LinkedHashSet<CombineKey> set = lfuQueue.get(preCount);
                    set.remove(combineKey);
                    if (set.isEmpty()) lfuQueue.remove(preCount);
                    long currentCount = preCount+1;
                    set = lfuQueue.get(currentCount);
                    if (set == null) {
                        set = new LinkedHashSet<>();
                        lfuQueue.put(currentCount, set);
                    }
                    set.add(combineKey);
                    visitCount.put(combineKey, currentCount);
                }
                break;
        }
    }

    /**
     * 新增缓存更新缓存队列
     * @param keys key
     */
    private void updateCacheQueueAfterAdd(Object... keys) {
        switch (strategy) {
            case FIFO:
                fifoQueue.addLast(new CombineKey(keys));
                break;
            case LRU:
            {
                long currentTime = System.currentTimeMillis();
                CombineKey combineKey = new CombineKey(keys);
                LinkedHashSet<CombineKey> set = lruQueue.get(currentTime);
                if (set == null) {
                    set = new LinkedHashSet<>();
                    lruQueue.put(currentTime, set);
                }
                set.add(combineKey);
                visitTime.put(combineKey, currentTime);
            }
            break;
            case LFU:
            {
                CombineKey combineKey = new CombineKey(keys);
                LinkedHashSet<CombineKey> set = lfuQueue.get(1L);
                if (set == null) {
                    set = new LinkedHashSet<>();
                    lfuQueue.put(1L, set);
                }
                set.add(combineKey);
                visitCount.put(combineKey, 1L);
            }
            break;
        }
    }

    /**
     * 新增缓存更新缓存队列
     * @param keys key
     */
    private void updateCacheQueueAfterRomove(Object... keys) {
        switch (strategy) {
            case FIFO:
                //暂不处理,
                break;
            case LRU:
                {
                    CombineKey combineKey = new CombineKey(keys);
                    long preTime = visitTime.get(combineKey);
                    LinkedHashSet<CombineKey> set = lruQueue.get(preTime);
                    set.remove(combineKey);
                    if (set.isEmpty()) lruQueue.remove(preTime);
                    visitTime.remove(combineKey);
                }
                break;
            case LFU:
                {
                    CombineKey combineKey = new CombineKey(keys);
                    long preCount = visitCount.get(combineKey);
                    LinkedHashSet<CombineKey> set = lfuQueue.get(preCount);
                    set.remove(combineKey);
                    if (set.isEmpty()) lfuQueue.remove(preCount);
                    visitCount.remove(combineKey);
                }
                break;
        }
    }
}