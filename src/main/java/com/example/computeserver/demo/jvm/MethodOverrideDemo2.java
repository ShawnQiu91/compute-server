package com.example.computeserver.demo.jvm;

/**
 * 钩子方法示例
 */
public class MethodOverrideDemo2 {
    public void m1() {
        m2();
    }

    /**
     * 钩子方法，在子类中会重写
     */
    public void m2() {
        System.out.printf("super's m2!");
    }

    public static void main(String[] args) {
        MethodOverrideDemo2 demo2 = new sub();
        demo2.m1();
    }
}

class sub extends MethodOverrideDemo2 {
    public void m2() {
        System.out.printf("sub's m2!");
    }
}
