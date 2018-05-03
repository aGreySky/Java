package per.agreysky.impl;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.dao.BaseDAO;
import per.agreysky.dao.StudentDao;
import per.agreysky.vo.Student;
public class StudentDaoImpl extends BaseDAO implements StudentDao {
    public Student selectStudent(String readerId) {
        Session session = null;
        Transaction tx = null;
        Student stu = null;
        try {
            session = getSession();
            tx = session.beginTransaction();
            stu = (Student) session.get(Student.class, readerId);
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
}
