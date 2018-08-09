package com.example.computeserver.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试同一个lock生成的不同condition是否相互影响
 * 结果：
 * 不会相互影响，一个condition.await必须用同一个condition.signal唤醒
 * 使用场景：
 * 不同类型的线程（例如：生产者、消费者线程）同时等待，唤醒指定某一类线程（如：消费者）
 */
public class LockConditionDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ConditionDemo(0));
        t1.start();
        //6秒后再调用另一个线程
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(new ConditionDemo(1));
        t2.start();

/*
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/

    }
}

class ConditionDemo implements Runnable {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition notEmpty = lock.newCondition();
    private static Condition notFull = lock.newCondition();
    private static int capacity = 6;
    private static int count = 6;
    private static int flag = 0;

    public ConditionDemo(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag == 0) {
            put();
        } else {
            get();
        }
    }

    public static void put() {
        try {
            lock.lockInterruptibly();
            if (count == 6) {
                System.out.println("#################### put await ###################");
                notEmpty.await();//用notEmpty等待
            }
            System.out.println("#################### put run ###################");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void get() {
        try {
            lock.lockInterruptibly();
            System.out.println("#################### get run ###################");
            count = 0;
            notFull.signal();//用notFull唤醒
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}



