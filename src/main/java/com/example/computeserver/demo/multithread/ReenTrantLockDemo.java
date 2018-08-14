package com.example.computeserver.demo.multithread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 与synchronized相比ReenTrantLock是加在对象（类、实例）上，还是临界资源上？
 * 结果：
 * 1、当两个线程同时请求同一临界资源时，产生同步控制
 * 2、当两个线程同时请求同一类对象的不同临界资源时(用不同的lock)，不会产生同步控制
 * 3、当两个线程同时请求同一类对象的不同临界资源时(用相同的lock)，产生同步控制
 * 结论：
 * 1、被同一个lock加锁的不同资源被多线程请求时会产生同步控制（lock对同一个线程可重入，对多个线程不可重入）
 * 2、被不同lock加锁的不同资源被多线程请求时不会产生同步控制
 * 3、与synchronized相比，synchronized锁是加在整个对象（类或实例）上，而lock锁是加在被lock-unlock包围的块上。
 */
public class ReenTrantLockDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ReenTrant(0));
        Thread t2 = new Thread(new ReenTrant(1));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("num : "+ ReenTrant.getN());
    }
}

class ReenTrant implements Runnable {
    private int flag;
    private static ReentrantLock lock1 = new ReentrantLock();
    private static ReentrantLock lock2 = new ReentrantLock();
    private static int n;

    public static int getN() {
        return n;
    }

    public ReenTrant(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 0) {
            m1();
        } else {
            m2();
        }

    }

    public static void m1() {
        for (int i = 0; i < 100000; i++) {
            try {
                lock1.lock();
                n++;
            } finally {
                lock1.unlock();
            }
        }
    }

    public static void m2() {
        for (int i = 0; i < 100000; i++) {
            try {
                lock2.lock();
                n++;
            } finally {
                lock2.unlock();
            }
        }
    }
}


