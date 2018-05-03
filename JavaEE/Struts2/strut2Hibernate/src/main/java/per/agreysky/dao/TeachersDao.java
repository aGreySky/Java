package per.agreysky.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.Interceptor.EntityBeanInterceptor;
import per.agreysky.bean.Teachers;
import per.agreysky.util.HibernateUtil;

public class TeachersDao {
    private static Map<Integer, Teachers> emps = new LinkedHashMap<Integer, Teachers>();

    public List<Teachers> getTeachers() {

        ArrayList<Teachers> list = new ArrayList<Teachers>();
        Session session = HibernateUtil.getSession(new EntityBeanInterceptor()); //获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Query query = session.createQuery("from Teachers");
            list = (ArrayList<Teachers>) query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

        return list;
    }

    public void delete(Teachers t) {
        Session session = HibernateUtil.getSession(new EntityBeanInterceptor()); //获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.delete(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void save(Teachers t) {
        Session session = HibernateUtil.getSession(new EntityBeanInterceptor()); //获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.save(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Teachers get(Long id) {
        Session session = HibernateUtil.getSession(new EntityBeanInterceptor()); //获取会话
        Transaction tx = session.beginTransaction();//创建事务
        Teachers t = null;
        try {
            Query query = session.createQuery("from Teachers where Id=?");
            query.setParameter(0, id);
            query.setMaxResults(1);
            t = (Teachers) query.uniqueResult();
            tx.commit();//提交事务
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
    public void update(Teachers t) {
        Session session = HibernateUtil.getSession(new EntityBeanInterceptor()); //获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.update(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

}
