package com.example.computeserver.demo.jvm;

/**
 * 静态分派示例
 * 根据静态类型确定方法版本
 */
public class MethodOverLoadDemo {
    public static class SuperClass {
    }

    public static class Sub1 extends SuperClass {
    }

    public static class Sub2 extends SuperClass {
    }

    public void sayHello(SuperClass s) {
        System.out.println("hello super");
    }

    public void sayHello(Sub1 s) {
        System.out.println("hello sub1");
    }

    public void sayHello(Sub2 s) {
        System.out.println("hello sub2");
    }

    public static void main(String[] args) {
        SuperClass s1 = new Sub1();
        SuperClass s2 = new Sub2();
        MethodOverLoadDemo ml= new MethodOverLoadDemo();
        ml.sayHello(s1);
        ml.sayHello(s2);
    }
}
