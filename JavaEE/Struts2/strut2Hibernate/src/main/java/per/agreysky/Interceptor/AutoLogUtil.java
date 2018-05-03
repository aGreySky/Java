package per.agreysky.Interceptor;

import java.util.Date;

import org.hibernate.Session;

import per.agreysky.bean.AutoLog;
import per.agreysky.bean.Teachers;
import per.agreysky.util.HibernateUtil;

public class AutoLogUtil {
    public static void LogIt(String action, Teachers entity) {

        Session tempSession = HibernateUtil.getSessionFactory().openSession();

        try {
            tempSession.getTransaction().begin();
            AutoLog auditRecord = new AutoLog(action, entity.getDetail(),
                    new Date(), entity.getId(), entity.getClass().toString());
            tempSession.save(auditRecord);
            tempSession.getTransaction().commit();

        } finally {
            tempSession.close();

        }

    }

}
