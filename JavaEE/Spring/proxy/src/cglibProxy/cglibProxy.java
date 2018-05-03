package cglibProxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class cglibProxy implements MethodInterceptor {
    private Enhancer enhance = new Enhancer();
    public Object getProxy(Class clazz) {
        enhance.setSuperclass(clazz);
        enhance.setCallback(this);
        return enhance.create();
    }

    /**
     * ��������Ŀ���෽���ĵ���
     * obj Ŀ�����ʵ��
     * m Ŀ�귽���ķ������
     * args �����Ĳ���
     * proxy�������ʵ��
     */
    @Override
    public Object intercept(Object obj, Method m, Object[] args,
            MethodProxy proxy) throws Throwable {
        System.out.println("��־��ʼ..");
        //��������ø���ķ���
        proxy.invokeSuper(obj, args);
        System.out.println("��־����,,,");
        return null;
    }

}
