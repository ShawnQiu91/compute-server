package com.example.computeserver.singleton;

/**
 * 以枚举来实现单例模式
 */
enum SingletonEnum {
    INSTANCE;
    private Instance instance;
    SingletonEnum(){
        instance = new Instance();
    }
    public Instance getInstance() {
        return instance;
    }

}

/**
 * 测试实例类
 */
class Instance {}

public class SingletonInstance{
    public static void main(String[] args) {
        Instance ins1 = SingletonEnum.INSTANCE.getInstance();
        Instance ins2 = SingletonEnum.INSTANCE.getInstance();
        System.out.println(ins1 == ins2);
    }
}
