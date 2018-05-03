package per.agreysky.org.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.org.dao.AdminDAO;
import per.agreysky.org.dao.BaseDAO;
import per.agreysky.org.vo.Admin;

public class AdminDAOImpl extends BaseDAO implements AdminDAO {
    //管理员登录
    @Override
    public Admin checkAdminLogin(String adminId, String password) {
        Admin admin = null;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();
        try {
            Query query = session
                    .createQuery("from Admin where adminId=? and password=?");
            query.setParameter(0, adminId);
            query.setParameter(1, password);
            query.setMaxResults(1);
            admin = (Admin) query.uniqueResult();//执行查询
            tx.commit();//提交事务
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return admin;
    }
}
