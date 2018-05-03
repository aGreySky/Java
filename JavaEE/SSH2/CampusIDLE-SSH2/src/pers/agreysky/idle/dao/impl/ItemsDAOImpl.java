package pers.agreysky.idle.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pers.agreysky.idle.dao.BaseDAO;
import pers.agreysky.idle.dao.ItemsDAO;
import pers.agreysky.idle.dto.Items;
import pers.agreysky.idle.vo.AllEntityVO;

public class ItemsDAOImpl extends BaseDAO implements ItemsDAO {
    Session session = null;
    Transaction tx = null;
    //ͨ������id�����ҵ�����
    @Override
    public Items getFriendsById(Integer id) {
        Items item = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from Items where id =?");
            query.setParameter(0, id);
            query.setMaxResults(1);
            item = (Items) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return item;
    }
    //ɾ���ҵ�һ������
    @Override
    public boolean deleteItem(Items item) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.delete(item);
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
    //�����Ƿ����
    @Override
    public boolean ifItemsExist(String overview, String contact) {
        Items item = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Items where overview =? and contact=?");
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
    //��ȡ���һ�����õ�id
    @Override
    public int getLatestId() {
        Items item = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Items order by id DESC limit 1");
            query.setMaxResults(1);
            item = (Items) query.uniqueResult();
            tx.commit();
            if (item != null) {
                return item.getId();
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
    //�������
    @Override
    public boolean addItems(Items item) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.save(item);
            tx.commit();
            //            String sql = "insert into items (name,price,overview,type,way,contact,ifNew,origPrice,picture,userId) value(?,?,?,?,?,?,?,?,?,?) ";// ��ӵ�sql���
            //            SQLQuery query = session.createSQLQuery(sql);
            //            int i = 0;
            //            for (; i < update.length; i++) {
            //                query.setParameter(i, update[i]);
            //            }
            //            query.setParameter(i, id);
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
    //�޸�����
    @Override
    public boolean updateItems(Items item) {
        try {
            session = getSession();
            tx = session.beginTransaction();
            session.update(item);
            tx.commit();
            //            String sql = "update items set name=? ,price=? , overview=?, type=? ,way=? ,contact=? ,ifNew=? ,origPrice ,picture=? where id=?";
            //            SQLQuery query = session.createSQLQuery(sql);
            //            int i = 0;
            //            for (; i < update.length; i++) {
            //                query.setParameter(i, update[i]);
            //            }
            //            query.setParameter(i, id);
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
    //ͨ�����Ͳ�������
    @Override
    public ArrayList<Items> getItemsByType(String type) {
        ArrayList<Items> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    "from Items where type=?");
            query.setParameter(0, type);
            list = (ArrayList<Items>) query.list();
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
    //ͨ��������������
    @Override
    public ArrayList<AllEntityVO> getItemsByUserCdn(String itemsSql) {
        ArrayList<AllEntityVO> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(
                    itemsSql);
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

    //ͨ���û�id������Ʒ��Ϣ
    @Override
    public List getItemListByUserId(Integer id) {
        ArrayList<Items> list = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from Items where userId =?");
            query.setInteger(0, id);
            list = (ArrayList<Items>) query.list();
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
