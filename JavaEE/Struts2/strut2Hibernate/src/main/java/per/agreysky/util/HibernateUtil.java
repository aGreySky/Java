package per.agreysky.util;

import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static Configuration configuration = new Configuration();
    public static Session getSession(Interceptor... interceptor) {

        Session session = threadLocal.get();
        if (session == null || !session.isOpen()) {
            //如果session为空重新建立一个Session工厂  
            if (sessionFactory == null) {
                getSessionFactory();
            }
            //如果interceptor参数值中包含拦截器对象，则安装该拦截器  
            session = (sessionFactory != null)
                    ? ((interceptor.length == 0)
                            ? sessionFactory.openSession()
                            : sessionFactory.openSession(interceptor[0]))
                    : null;
            //如果ThreadLocal对象中没有属于当前线程的session对象，则添加一个Session对象  
            threadLocal.set(session);
        }
        return session;
    }
    static {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static Session getSession() throws HibernateException {
        Session session = threadLocal.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }
    public static void closeSession() throws HibernateException {
        Session session = threadLocal.get();
        if (session != null) {
            session.close();
        }
        threadLocal.set(null);
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
