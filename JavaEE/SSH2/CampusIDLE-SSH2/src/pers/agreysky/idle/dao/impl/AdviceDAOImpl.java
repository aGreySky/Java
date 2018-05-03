package pers.agreysky.idle.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pers.agreysky.idle.dao.AdviceDAO;
import pers.agreysky.idle.dao.BaseDAO;
import pers.agreysky.idle.dto.Advice;

public class AdviceDAOImpl extends BaseDAO implements AdviceDAO {
    Session session = null;//会话对象
    Transaction tx = null;//事物对象

    //添加建议
    @Override
    public boolean addAdvice(Advice ad) {
        try {
            session = getSession();//获取会话
            tx = session.beginTransaction();//创建事务
            session.save(ad);//保存操作
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
