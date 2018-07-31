package com.example.computeserver;

import org.junit.Test;

public class JVMTest {

    public static class O {
        public void m1(O o) {
            System.out.println("O-m1");
        }
    }

    public static class A extends O{
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


    @Test
    public void name() throws Exception {
        A b = new B();
        b.m1(b);

        B bb = new B();
        b.m1(bb);

        bb.m1(bb);
    }
}
