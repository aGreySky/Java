package jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        Car car = new Car();
        InvocationHandler h = new TimeHandle(car);
        Class<?> cls = car.getClass();
        /**
         * loader �������
         * interfaces ʵ�ֽӿ�
         * h InvocationHandle
         */
        Moveable m = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(),
                cls.getInterfaces(), h);
        m.move();
    }

}
