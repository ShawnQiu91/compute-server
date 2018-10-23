package com.example.computeserver.demo.jvm;

/**
 * 动态分派示例
 * 方法重写-jvm选择方法版本：
 * 1、在编译期间根据静态类型选择一个方法版本
 * 2、在运行期间根据实际类型和编译期间已选的版本选择最终版本
 */
public class MethodOverrideDemo {

    public static class O {
        public void m1(O o) {
            System.out.println("O-m1");
        }
    }

    public static class A extends O {
        public void m1(A a) {
            System.out.println("A-m1");
        }
    }

    public static class B extends A {
        @Override
        public void m1(A a) {
            System.out.println("B-m1");
        }

        public void m1(B b) {
            System.out.println("B-m2");
        }

        public void m1(O b) {
            System.out.println("B-m3");
        }
    }


    public static void main(String[] args) {
        A a = new B();
        a.m1(a);

        B b = new B();
        a.m1(b);

        b.m1(b);
    }
}
