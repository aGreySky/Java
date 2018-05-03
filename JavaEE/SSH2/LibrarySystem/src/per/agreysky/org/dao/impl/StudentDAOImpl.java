package per.agreysky.org.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.org.dao.BaseDAO;
import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.vo.Student;

public class StudentDAOImpl extends BaseDAO implements StudentDAO {

    //通过借书证号查找学生
    @Override
    public Student selectByReaderId(String readerId) {
        Student stu = null;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
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
    //学生新加
    @Override
    public void addStudent(Student stu) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.save(stu);//保存操作
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
    //学生删除
    @Override
    public void deleteStudent(Student stu) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
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
    //学生修改
    @Override
    public void updateStudent(Student stu) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.update(stu);//修改操作
            tx.commit();//提交事务
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    //学生登录
    @Override
    public Student checkStudentLogin(String readerId, String password) {
        Student student = null;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Query query = session.createQuery(
                    "from Student where readerId=? and password=?");
            query.setParameter(0, readerId);
            query.setParameter(1, password);
            query.setMaxResults(1);
            student = (Student) query.uniqueResult();//执行查询
            tx.commit();//提交事务
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
