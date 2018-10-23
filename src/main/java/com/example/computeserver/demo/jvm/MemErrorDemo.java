package com.example.computeserver.demo.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * jvm内存溢出示例
 * -Xms 堆初始值
 * -Xmx 堆最大值
 * -Xmn 新生代大小
 * -Xss 每个线程的栈大小
 * -server -Xmx20m -Xms20m -Xmn10m -Xss1m -XX:+HeapDumpOnOutOfMemoryError
 * -server -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -XX:+PrintGCDetails  -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC
 */
public class MemErrorDemo {
    int depth = 0;

    /**
     * 内存溢出错误（OOM）
     */
    public void OOMError() {
        List<byte[]> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(new byte[5 * 1024 * 1024]);
            System.out.println("loop count: " + (++i));
        }
    }

    /**
     * 栈溢出错误（StackOverFlowError）
     */
    public void SOFError() {
        try {
            depth++;
            SOFError();
        } finally {
            System.out.println("递归count: " + depth);
        }
    }

    /**
     * 元空间错误
     * 使用cglib生成动态代理类
     */
    public void MetaSpaceError() {
        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMObject.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                        return proxy.invokeSuper(obj, args);
                    }
                });
                enhancer.create();
            }
        } catch (Exception e) {
            System.out.println("generate class count" + i);
            e.printStackTrace();
        }
    }

    static class OOMObject {

    }

    public static void main(String args[]) {
        MemErrorDemo memErrorDemo = new MemErrorDemo();
        //memErrorDemo.OOMError();
        //memErrorDemo.SOFError();
        memErrorDemo.MetaSpaceError();
    }
}
