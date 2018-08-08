package com.example.computeserver.multithread;

/**
 * synchronized 示例
 * 此例子证明synchronized的类锁是加在类上，而非加在类方法上
 * 线程1执行类的一个加锁方法a，获取到类锁未释放，同时线程2访问类的另一个加锁方法b，
 * b会在a执行完成后执行； 若线程2访问的是非加锁方法，则可以正常执行。
 * 结论：1、虽然锁是加在类或对象上，但只有多个线程同时访问同一个锁下的 *临界资源* 时才会进行同步控制
 *       2、访问同一锁下的不同*临界资源*时也需要同步控制
 */
public class SynchronizedDemo implements Runnable {

    public static synchronized void increasea() {
        System.out.println(" a : start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" a : end");
    }

    public static synchronized void increaseb() {
        System.out.println(" b : start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" b : end");
    }

    @Override
    public void run() {
        increasea();

    }

    public static class OtherThread implements Runnable {
        @Override
        public void run() {
            increaseb();
        }
    }

    public static void main(String[] args) {
        Thread inst1 = new Thread(new SynchronizedDemo());
        Thread inst2 = new Thread(new SynchronizedDemo.OtherThread());
        inst2.start();
        inst1.start();
        try {
            inst1.join();
            inst2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
