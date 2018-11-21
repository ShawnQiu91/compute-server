package com.example.computeserver.demo.jvm;

/**
 * 字符串对象创建时内存分配
 * 字符串常量池（内容在编译期确定）、堆
 * 字面量在字符串常量池中建一个对象；new String（）会在堆、字符串常量池中各建一个对象
 * 编译期、运行期
 * 使用intern可以释放堆中字符串对象，节省内存空间
 */
public class StringObjDemo {
    public static void main(String args[]) {
        String a = "he";
        String b = "llo";

        String c = "hello";
        String d = "he" + "llo";
        String e = new String("hello");
        String f = a + "llo";
        String g = a + b;
        String h = e.intern();

        System.out.println(c == d);
        System.out.println(c == e);
        System.out.println(c == f);
        System.out.println(c == g);
        System.out.println(f == g);
        System.out.println(c == h);
    }
}

