package com.example.computeserver.demo.jvm;

/**
 * 方法重写-jvm选择方法版本：
 * 1、在编译期间根据静态类型选择一个方法版本
 * 2、在运行期间根据实际类型和编译期间已选的版本选择最终版本
 */
public class JVMDemo {

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
        A b = new B();
        b.m1(b);

        B bb = new B();
        b.m1(bb);

        bb.m1(bb);
    }
}
