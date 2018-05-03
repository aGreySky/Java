package jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimeHandle implements InvocationHandler {

    private Object target;

    public TimeHandle(Object target) {
        this.target = target;
    }

    /**
     * 参数
     * proxy：被代理对象
     * method:被代理对象的返回方法
     * args方法的参数
     * 
     * 返回值
     * Object方法的返回值
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        long starttime = System.currentTimeMillis();
        System.out.println("汽车开始行驶...");
        method.invoke(target);
        long endtime = System.currentTimeMillis();
        System.out.println("汽车结束行驶...汽车行驶时间：" + (endtime - starttime) + "毫秒！");
        return null;
    }

}
