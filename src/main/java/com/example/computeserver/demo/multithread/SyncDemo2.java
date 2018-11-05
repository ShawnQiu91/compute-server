package com.example.computeserver.demo.multithread;

/**
 * synchronized示例
 * 1、修饰静态方法
 * 2、修饰实例方法
 * 3、修饰代码块
 */
public class SyncDemo2 {
    private static int num = 0;

    /**
     * 修饰静态方法
     */
    public static synchronized void count1() {
        for (int i = 0; i < 100000000; i++) {
            num++;
        }
    }

    /**
     * 修饰实例方法
     */
    public synchronized void count2() {
        for (int i = 0; i < 100000000; i++) {
            num++;
        }
    }

    /**
     * 修饰代码块
     * 效果与修饰静态方法相同
     */
    public void count3() {
        synchronized(SyncDemo2.class) {
            for (int i = 0; i < 100000000; i++) {
                num++;
            }
        }
    }

    /**
     * 修饰代码块
     * 效果与修饰实例方法相同
     */
    public void count4() {
        synchronized(this) {
            for (int i = 0; i < 100000000; i++) {
                num++;
            }
        }
    }

    public static void main(String[] args) {
        //两个线程运行一个类的两个对象，运行类的静态方法count1，
        //产生同步，num=200000000

        //两个线程运行一个类的两个对象，运行类的实例方法count2
        //因为调用的是不同的对象，并未产生同步，num<=200000000
        SyncDemo2 syncDemo1 = new SyncDemo2();
        SyncDemo2 syncDemo2 = new SyncDemo2();

        //两个线程运行一个对象，运行类的实例方法count2
        //因为调用的是同一个对象，产生同步，num=200000000
        //SyncDemo2 syncDemo3 = new SyncDemo2();
        //syncDemo1 = syncDemo3;
        //syncDemo2 = syncDemo3;

        //启动两个线程进行运算
        Thread thread1 = new Thread(new ThreadDemo(syncDemo1));
        Thread thread2 = new Thread(new ThreadDemo(syncDemo2));
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(SyncDemo2.num);
    }
}

class ThreadDemo implements Runnable {
    SyncDemo2 syncDemo2;
    public ThreadDemo(SyncDemo2 syncDemo2){
        this.syncDemo2 = syncDemo2;
    }

    @Override
    public void run() {
        //syncDemo2.count1();
        //syncDemo2.count2();
        syncDemo2.count3();
        //syncDemo2.count4();
    }
}





