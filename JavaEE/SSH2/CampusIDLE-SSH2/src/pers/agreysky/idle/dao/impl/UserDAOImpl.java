package pers.agreysky.idle.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pers.agreysky.idle.dao.BaseDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.User;

public class UserDAOImpl extends BaseDAO implements UserDAO {
    Session session = null;//�Ự����
    Transaction tx = null;//�������

    @Override
    //ͨ���ֻ��ŵ�¼
    public User ifRightByPhone(User user) {
        try {
            session = getSession();//��ȡ�Ự
            tx = session.beginTransaction();//��������
            Query query = session
                    .createQuery("from User where phone=? and password=?");
            query.setParameter(0, user.getPhone());
            query.setParameter(1, user.getPassword());
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();//�ύ����
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    //ͨ�������¼
    @Override
    public User ifRightByUseremail(User user) {
        try {
            session = getSession();//��ȡ�Ự
            tx = session.beginTransaction();//��������
            Query query = session
                    .createQuery("from User where useremail=? and password=?");
            query.setParameter(0, user.getUseremail());
            query.setParameter(1, user.getPassword());
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();//�ύ����
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    //ͨ���û�����¼
    @Override
    public User ifRightByUsername(User user) {
        try {
            session = getSession();//��ȡ�Ự
            tx = session.beginTransaction();//��������
            Query query = session
                    .createQuery("from User where username=? and password=?");
            query.setParameter(0, user.getUsername());
            query.setParameter(1, user.getPassword());
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();//�ύ����
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    //�û��Ƿ����
    @Override
    public boolean ifExist(User user) {
        try {
            session = getSession();//��ȡ�Ự
            tx = session.beginTransaction();//��������
            Query query = session.createQuery("from User where username=?");
            query.setParameter(0, user.getUsername());
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();//�ύ����
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        if (user != null) {
            return true;
        }
        return false;
    }
    //����û�
    @Override
    public void addUser(User user) {
        try {
            session = getSession();//��ȡ�Ự
            tx = session.beginTransaction();//��������
            session.save(user);//�������
            tx.commit();//�ύ����
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    //ͨ��id�����û�
    @Override
    public User getUserByUserId(Integer id) {
        User user = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where id =?");
            query.setParameter(0, id);
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    //ͨ���ֻ��Ų����û�
    @Override
    public User getUserByPhone(String phone) {
        User user = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("from User where phone =?");
            query.setParameter(0, phone);
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    //�����û�
    @Override
    public boolean updateUser(User newuser) {
        try {
            session = getSession();//��ȡ�Ự
            tx = session.beginTransaction();//��������
            session.update(newuser);//�������
            tx.commit();//�ύ����
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
}
