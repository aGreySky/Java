package pers.agreysky.idle.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pers.agreysky.idle.dao.BaseDAO;
import pers.agreysky.idle.dao.JobsDAO;
import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.dto.Jobs;
import pers.agreysky.idle.vo.AllEntityVO;

public class JobsDAOImpl extends BaseDAO implements JobsDAO {
    Session session = null;
    Transaction tx = null;
    //删除一件兼职
    @Override
    public boolean deleteJobs(Jobs job) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.delete(job);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    //添加兼职
    @Override
    public boolean addJobs(Jobs job) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.save(job);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    //修改一件兼职
    @Override
    public boolean updateJobs(Jobs job) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.update(job);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return true;
    }
    //通过类型查找兼职
    @Override
    public ArrayList<Jobs> getJobsByType(String type) {
        ArrayList<Jobs> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Jobs where type=?");
            query.setParameter(0, type);
            list = (ArrayList<Jobs>) query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    //通过条件查找兼职
    @Override
    public ArrayList<AllEntityVO> getJobsByUserCdn(String jobsSql) {
        ArrayList<AllEntityVO> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    jobsSql);
            list = (ArrayList<AllEntityVO>) query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
    //兼职是否存在
    @Override
    public boolean ifJobsExist(String overview, String contact) {
        Items item = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Jobs where overview =? and contact=?");
            query.setParameter(0, overview);
            query.setParameter(1, contact);
            query.setMaxResults(1);
            item = (Items) query.uniqueResult();
            tx.commit();
            if (item != null) {
                return true;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
