package com.sqt.edu.nacos.threadtest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2020-04-21 9:37
 */
public class ThreadLocalTest {
    public static ThreadLocal<Integer> threadLocal = null;

    public static void main(String[] args) throws InterruptedException {
        threadLocal = new ThreadLocal<Integer>() {
            /**
             * 通过重写该方法来初始化ThreadLocal的值
             */
            @Override
            protected Integer initialValue() {
                return 10;
            }
        };

        MyThread t1 = new MyThread(20);
        MyThread t2 = new MyThread(30);
        t1.start();
        t1.join();
        t2.start();
        System.out.println(threadLocal.get());
    }

    static class MyThread extends Thread {

        private int val = 0;

        MyThread(int val) {
            this.val = val;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "-BEFORE-" + ThreadLocalTest.threadLocal.get());
            ThreadLocalTest.threadLocal.set(val);
            System.out.println(Thread.currentThread() + "-AFTER-" + ThreadLocalTest.threadLocal.get());
        }
    }

}
