package per.agreysky.impl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.dao.BaseDAO;
import per.agreysky.dao.LoginDao;
import per.agreysky.vo.Login;
public class LoginDaoImpl extends BaseDAO implements LoginDao {//++
    public Login checkLogin(String name, String password) {
        Session session = null;
        Transaction tx = null;
        Login login = null;
        try {
            session = getSession();//++
            tx = session.beginTransaction();
            Query query = session
                    .createQuery("from Login where name=? and password=?");
            query.setParameter(0, name);
            query.setParameter(1, password);
            query.setMaxResults(1);
            login = (Login) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return login;
    }
}
