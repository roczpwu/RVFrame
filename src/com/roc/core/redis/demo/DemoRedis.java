package com.roc.core.redis.demo;

import com.roc.core.redis.BaseRedis;

/**
 * User: rocwu
 * Date: 2017/3/9
 * Time: 下午4:50
 * Desc:
 */
public class DemoRedis extends BaseRedis {
    @Override
    protected int getBid() {
        return 1;
    }

    public static void main(String[] args) {
        DemoRedis demoRedis = new DemoRedis();
        DemoBean demoBean = new DemoBean();
        demoBean.setName("rocwu");
        demoRedis.set("1", "1");
        System.out.println(demoRedis.get("1"));
        demoRedis.setBean("1", demoBean);
        DemoBean newDemoBean = (DemoBean) demoRedis.getBean("1", DemoBean.class);
        System.out.println(newDemoBean);
    }
}
