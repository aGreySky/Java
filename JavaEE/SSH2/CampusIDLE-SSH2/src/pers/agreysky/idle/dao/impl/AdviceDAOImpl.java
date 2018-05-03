package pers.agreysky.idle.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pers.agreysky.idle.dao.AdviceDAO;
import pers.agreysky.idle.dao.BaseDAO;
import pers.agreysky.idle.dto.Advice;

public class AdviceDAOImpl extends BaseDAO implements AdviceDAO {
    Session session = null;//�Ự����
    Transaction tx = null;//�������

    //��ӽ���
    @Override
    public boolean addAdvice(Advice ad) {
        try {
            session = getSession();//��ȡ�Ự
            tx = session.beginTransaction();//��������
            session.save(ad);//�������
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
