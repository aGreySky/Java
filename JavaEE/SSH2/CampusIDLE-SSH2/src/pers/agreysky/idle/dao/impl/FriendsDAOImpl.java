package pers.agreysky.idle.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pers.agreysky.idle.dao.BaseDAO;
import pers.agreysky.idle.dao.FriendsDAO;
import pers.agreysky.idle.dto.Friends;
import pers.agreysky.idle.vo.AllEntityVO;

public class FriendsDAOImpl extends BaseDAO implements FriendsDAO {
    Session session = null;
    Transaction tx = null;

    //通过id查找我的某个交友
    @Override
    public Friends getFriendsById(Integer id) {
        Friends fr = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from Friends where id =?");
            query.setParameter(0, id);
            query.setMaxResults(1);
            fr = (Friends) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return fr;
    }
    //更新我的某个交友
    @Override
    public boolean updateFriends(Friends friend) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.update(friend);
            tx.commit();
            //            String sql = "update friends set overview=? ,way=? ,contact=? ,picture=? where id=?";
            //            SQLQuery query = session.createSQLQuery(sql);
            //            int i = 0;
            //            for (; i < update.length; i++) {
            //                query.setParameter(i + 1, update[i]);
            //            }
            //            query.setParameter(i + 1, id);
            //            tx.commit();
            //            if (query.executeUpdate() > 0) {
            //                return true;
            //            } else {
            //                System.out.println(sql);
            //            }
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
    //删除我的某个交友
    @Override
    public boolean deleteFriends(Friends friend) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.delete(friend);
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
    //查看交友是否存在
    @Override
    public boolean ifFriendsExist(String overview, String contact) {
        Friends fr = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Friends where overview =? and contact=?");
            query.setParameter(0, overview);
            query.setParameter(1, contact);
            query.setMaxResults(1);
            fr = (Friends) query.uniqueResult();
            tx.commit();
            if (fr != null) {
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
    //获取最后一件交友的id
    @Override
    public int getLatestId() {
        Friends fr = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Friends order by id DESC limit 1");
            query.setMaxResults(1);
            fr = (Friends) query.uniqueResult();
            tx.commit();
            if (fr != null) {
                return fr.getId();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return 0;
    }
    //添加我的交友
    @Override
    public boolean addFriends(Friends friend) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.save(friend);
            tx.commit();
            //            String sql = "insert into friends (overview,way,contact,picture,userId) value(?,?,?,?,?) ";
            //            SQLQuery query = session.createSQLQuery(sql);
            //            int i = 0;
            //            for (; i < update.length; i++) {
            //                query.setParameter(i + 1, update[i]);
            //            }
            //            query.setParameter(i + 1, id);
            //            tx.commit();
            //            if (query.executeUpdate() > 0) {
            //                return true;
            //            } else {
            //                System.out.println(sql);
            //            }
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
    //通过类型查找交友
    @Override
    public ArrayList<Friends> getFriendsByType() {
        ArrayList<Friends> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Friends");
            list = (ArrayList<Friends>) query.list();
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
    //通过条件查找交友
    @Override
    public ArrayList<AllEntityVO> getFriendsByUserCdn(String friendsSql) {
        ArrayList<AllEntityVO> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    friendsSql);
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

}
