package per.agreysky.org.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.org.dao.BaseDAO;
import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.vo.Student;

public class StudentDAOImpl extends BaseDAO implements StudentDAO {

    //ͨ������֤�Ų���ѧ��
    @Override
    public Student selectByReaderId(String readerId) {
        Student stu = null;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            Query query = session.createQuery("from Student where readerId =?");
            query.setParameter(0, readerId);
            query.setMaxResults(1);
            stu = (Student) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return stu;
    }
    //ѧ���¼�
    @Override
    public void addStudent(Student stu) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.save(stu);//�������
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
    //ѧ��ɾ��
    @Override
    public void deleteStudent(Student stu) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.delete(stu);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    //ѧ���޸�
    @Override
    public void updateStudent(Student stu) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.update(stu);//�޸Ĳ���
            tx.commit();//�ύ����
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    //ѧ����¼
    @Override
    public Student checkStudentLogin(String readerId, String password) {
        Student student = null;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            Query query = session.createQuery(
                    "from Student where readerId=? and password=?");
            query.setParameter(0, readerId);
            query.setParameter(1, password);
            query.setMaxResults(1);
            student = (Student) query.uniqueResult();//ִ�в�ѯ
            tx.commit();//�ύ����
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return student;
    }

}
