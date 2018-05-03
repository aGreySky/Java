package pers.agreysky.idle.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pers.agreysky.idle.dao.BaseDAO;
import pers.agreysky.idle.dao.HelpsDAO;
import pers.agreysky.idle.dto.Helps;
import pers.agreysky.idle.vo.AllEntityVO;

public class HelpsDAOImpl extends BaseDAO implements HelpsDAO {
    Session session = null;
    Transaction tx = null;
    //通过id查找帮助
    @Override
    public Helps getHelpsById(Integer id) {
        Helps help = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from Helps where id =?");
            query.setParameter(0, id);
            query.setMaxResults(1);
            help = (Helps) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return help;
    }
    //删除一件我的帮助
    @Override
    public boolean deleteHelps(Helps help) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.delete(help);
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
    //添加一件我的帮助
    @Override
    public boolean addHelps(Helps help) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.save(help);
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
    //修改一件我的帮助
    @Override
    public boolean updateHelps(Helps help) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.update(help);
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
    //通过类型查找帮助
    @Override
    public ArrayList<Helps> getHelpsByType(String type) {
        ArrayList<Helps> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Helps where type=?");
            query.setParameter(0, type);
            list = (ArrayList<Helps>) query.list();
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
    //通过条件查找帮助
    @Override
    public ArrayList<AllEntityVO> getHelpsByUserCdn(String helpsSql) {
        ArrayList<AllEntityVO> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    helpsSql);
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

    //通过用户id查找帮助信息
    @Override
    public List getHelpListByUserId(Integer id) {
        ArrayList<Helps> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from Helps where userId =?");
            query.setInteger(0, id);
            list = (ArrayList<Helps>) query.list();
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
    @Override
    public boolean ifHelpsExist(String overview, String contact) {
        Helps helps = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Helps where overview =? and contact=?");
            query.setParameter(0, overview);
            query.setParameter(1, contact);
            query.setMaxResults(1);
            helps = (Helps) query.uniqueResult();
            tx.commit();
            if (helps != null) {
                return true;
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        return false;
    }
}
