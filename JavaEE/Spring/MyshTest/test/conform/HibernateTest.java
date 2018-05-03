package conform;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernateTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void test() {
        Session session = HibernateSessionFactory.getSession();
        System.out.println(session);
    }

}
