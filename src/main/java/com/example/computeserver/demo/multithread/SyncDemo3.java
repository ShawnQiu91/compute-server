package com.example.computeserver.demo.multithread;

import java.util.concurrent.TimeUnit;

/**
 * 示例：中断对执行synchronized方法线程的影响
 * 正在执行的线程响应中断
 * 正在阻塞的线程、执行中的线程都会标记中断状态，
 * 但阻塞的线程不会立刻处理中断，而是在进入临界区后再响应。
 */
public class SyncDemo3 {
    public static boolean flag = true;

    public static synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " hold resource!");
        while (flag) {
            if (!Thread.currentThread().isInterrupted()) {
                //不用sleep，因为sleep会对中断抛出异常
                Thread.yield();
            } else {
                System.out.println(Thread.currentThread().getName() + " interrupted and release !");
                return;
            }
        }
    }

    public static void main(String[] args) {
        SyncDemo3 syncDemo1 = new SyncDemo3();
        SyncDemo3 syncDemo2 = new SyncDemo3();
        //启动两个线程
        Thread thread1 = new Thread(new ThreadDemo3(syncDemo1), "thread1");
        Thread thread2 = new Thread(new ThreadDemo3(syncDemo2), "thread2");
        thread1.start();
        //休眠1秒，让thread1获取资源
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
        //休眠1秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //thread1中断
        thread1.interrupt();
        //thread2中断
        thread2.interrupt();

        if (thread1.isInterrupted()) {
            System.out.println("thread1 interrupt!");
        }
        if (thread2.isInterrupted()) {
            System.out.println("thread2 interrupt!");
        }

        //休眠1秒，让thread2获取资源
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

class ThreadDemo3 implements Runnable {
    SyncDemo3 syncDemo3;

    public ThreadDemo3(SyncDemo3 syncDemo3) {
        this.syncDemo3 = syncDemo3;
    }

    @Override
    public void run() {
        syncDemo3.m1();
    }
}