package com.roc.core.Utils.cache;

/**
 * User: rocwu
 * Date: 2016/11/17
 * Time: 19:31
 * Desc: 组合键值
 */
public class CombineKey {
    protected Object[] keys;

    public CombineKey(Object... objs) {
        keys = new Comparable[objs.length];
        for (int i = 0; i < objs.length; i++) {
            keys[i] = objs[i];
        }
    }

    @Override
    public int hashCode() {
        int result = 1;
        for (Object key : keys) {
            int elementHash = key.hashCode();
            result = 31 * result + elementHash;
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!this.getClass().equals(obj.getClass()))
            return false;
        CombineKey o = (CombineKey) obj;
        if (this.keys.length != o.keys.length) return false;
        for (int i = 0; i < keys.length; i++) {
            if (!keys[i].equals(o.keys[i])) return false;
        }
        return true;
    }
}
