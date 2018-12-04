package com.example.computeserver.demo.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureDemo {
    public static void main(String[] args) {


        FutureTask futureTask = new FutureTask<>(new Callable<String>(){
            @Override
            public String call() throws Exception {
                return null;
            }
        });
    }
}

