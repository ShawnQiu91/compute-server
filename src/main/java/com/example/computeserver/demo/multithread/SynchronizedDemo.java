package com.example.computeserver.demo.multithread;

/**
 * synchronized 示例
 * 此例子证明synchronized的类锁是加在类上，而非加在类方法上
 * 线程1执行类的一个加锁方法a，获取到类锁未释放，同时线程2访问类的另一个加锁方法b，b会在a执行完成后执行。
 * 若线程2访问的是非加锁方法，则可以正常执行，只有访问临界区时才需要检查线程是否持有对象的锁。
 * 结论：1、虽然锁是加在类或对象上，但只有多个线程同时访问同一个锁下的 *临界资源* 时才会进行同步控制
 * 2、访问同一锁下的不同*临界资源*时也需要同步控制
 * 3、可以把synchronized看作统一的lock（多个临界资源上加synchronized，相当于加同一个lock）
 */
public class SynchronizedDemo implements Runnable {
    private static int n = 0;

    public static synchronized void increasea() {
        for (int i = 0; i < 100000; i++) {
            n++;
        }
    }

    public static synchronized void increaseb() {
        for (int i = 0; i < 100000; i++) {
            n++;
        }
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
        System.out.println("num : "+ n);
    }

}