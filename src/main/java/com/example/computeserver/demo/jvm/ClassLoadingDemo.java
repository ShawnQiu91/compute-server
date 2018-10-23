package com.example.computeserver.demo.jvm;

/**
 * 类加载阶段，被动引用示例
 * 1、通过子类引用父类静态字段，不会导致子类初始化
 * 2、通过数组定义引用类不会触发此类的初始化。
 *    数组本身不通过类加载器创建，由java虚拟机直接创建。
 * 3、常量在编译期存入调用类的常量池，本质上没有直接引用到定义常量的类
 */
public class ClassLoadingDemo {
    public static void main(String[] args) {
        //System.out.println(SubClass.h);
        //SubClass[] scs = new SubClass[10];
        System.out.println(SubClass.w);
    }
}

class SuperClass {
    static {
        System.out.println("SuperClass init");
    }

    public static String h = "hello";
    public static final String w = "world";
}

class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init");
    }
}