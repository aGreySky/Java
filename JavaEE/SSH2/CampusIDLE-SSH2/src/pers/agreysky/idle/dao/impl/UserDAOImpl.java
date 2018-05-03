package pers.agreysky.idle.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import pers.agreysky.idle.dao.BaseDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.User;

public class UserDAOImpl extends BaseDAO implements UserDAO {
    Session session = null;//会话对象
    Transaction tx = null;//事物对象

    @Override
    //通过手机号登录
    public User ifRightByPhone(User user) {
        try {
            session = getSession();//获取会话
            tx = session.beginTransaction();//创建事务
            Query query = session
                    .createQuery("from User where phone=? and password=?");
            query.setParameter(0, user.getPhone());
            query.setParameter(1, user.getPassword());
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();//提交事务
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
    //通过邮箱登录
    @Override
    public User ifRightByUseremail(User user) {
        try {
            session = getSession();//获取会话
            tx = session.beginTransaction();//创建事务
            Query query = session
                    .createQuery("from User where useremail=? and password=?");
            query.setParameter(0, user.getUseremail());
            query.setParameter(1, user.getPassword());
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();//提交事务
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
    //通过用户名登录
    @Override
    public User ifRightByUsername(User user) {
        try {
            session = getSession();//获取会话
            tx = session.beginTransaction();//创建事务
            Query query = session
                    .createQuery("from User where username=? and password=?");
            query.setParameter(0, user.getUsername());
            query.setParameter(1, user.getPassword());
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();//提交事务
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
    //用户是否存在
    @Override
    public boolean ifExist(User user) {
        try {
            session = getSession();//获取会话
            tx = session.beginTransaction();//创建事务
            Query query = session.createQuery("from User where username=?");
            query.setParameter(0, user.getUsername());
            query.setMaxResults(1);
            user = (User) query.uniqueResult();
            tx.commit();//提交事务
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
    //添加用户
    @Override
    public void addUser(User user) {
        try {
            session = getSession();//获取会话
            tx = session.beginTransaction();//创建事务
            session.save(user);//保存操作
            tx.commit();//提交事务
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    //通过id查找用户
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
    //通过手机号查找用户
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
    //更新用户
    @Override
    public boolean updateUser(User newuser) {
        try {
            session = getSession();//获取会话
            tx = session.beginTransaction();//创建事务
            session.update(newuser);//保存操作
            tx.commit();//提交事务
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
