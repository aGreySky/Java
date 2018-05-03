package per.agreysky.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        Configuration configuration = new Configuration().configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        Session session = threadLocal.get();
        if (session == null) {
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public static void closeSession() {
        Session session = threadLocal.get();
        if (session != null) {
            session.close();
        }
    }

    public static void closeSessionFactory() {
        getSessionFactory().close();
    }
}
