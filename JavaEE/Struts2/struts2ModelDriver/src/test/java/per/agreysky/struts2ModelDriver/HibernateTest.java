package per.agreysky.struts2ModelDriver;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import per.agreysky.util.HibernateUtil;

public class HibernateTest {
    Session session;
    Transaction tx;

    @Before
    public void beforeExec() {
        session = HibernateUtil.getSession();
        tx = session.beginTransaction();
    }
    @After
    public void afterExec() {
        HibernateUtil.closeSession();
        HibernateUtil.shutdown();

    }
    @Test
    public void test0() {

    }
}