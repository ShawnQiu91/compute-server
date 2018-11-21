package com.example.computeserver.demo.jvm;

public class StringObjDemo2 {

    /**
     * JDK8以前，调用intern方法如果常量池中不存在，则在常量池中新建此对象
     * JDK8后，调用intern方法如果常量池中不存在，则在常量池中新建引用，引用堆中对象的地址
     * "+" 操作不会在常量池中建对象或引用
     * @param args
     */
    public static void main(String[] args) {

        //验证JDK8后在常量池中建引用（指向堆中地址）
        String a = new String("a") + new String("b");
        a.intern();
        String b = "ab";
        System.out.println(a==b);
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(b));

        System.out.println("------------------------------------------------------------------------");
        //验证“+”操作会不会在常量池中建对象或引用
        String c = "he";
        String d = c + "llo";
        //String e = d.intern();
        String f = "hello";

        System.out.println(System.identityHashCode(d));
        //System.out.println(System.identityHashCode(e));
        System.out.println(System.identityHashCode(f));
    }
}
