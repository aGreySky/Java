package per.agreysky.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.bean.Student;
import per.agreysky.util.HibernateUtil;

public class DemoJdbcDao {
    public List<Student> queryAllStudents() {
        Session s = HibernateUtil.getSession();
        Query query = s.createQuery("from Student");
        ArrayList<Student> list = (ArrayList<Student>) query.list();
        return list;
    }

    public void delStudent(Student stud) {
        Session s = HibernateUtil.getSession();
        Transaction tran = s.beginTransaction();
        try {
            s.delete(stud);

            //              Student stud2 = new Student();  
            //              stud2.setStudId("S001");  
            //              s.save(stud2);  
            tran.commit();
        } catch (HibernateException e) {
            //tran.rollback();
        }

    }

    public void addStudent(Student stud) {
        Session s = HibernateUtil.getSession();
        Transaction tran = s.beginTransaction();
        try {
            s.saveOrUpdate(stud);
            tran.commit();
        } catch (HibernateException e) {
        }
    }

    public List<Student> queryStudents(Student stud) {
        boolean f1 = false, f2 = false, f3 = false;
        Session s = HibernateUtil.getSession();
        String hql = "from Student s where 1=1";
        if (stud.getStudId() != null && stud.getStudId().trim().length() > 0) {
            hql = hql + " and s.studId=:studId";
            f1 = true;
        }
        if (stud.getStudName() != null
                && stud.getStudName().trim().length() > 0) {
            hql = hql + " and s.studName like :studName";
            f2 = true;
        }
        if (stud.getDeptId() != null && stud.getDeptId().trim().length() > 0) {
            hql = hql + " and s.deptId=:deptId";
            f3 = true;
        }

        Query query = s.createQuery(hql);
        if (f1) {
            query.setParameter("studId", stud.getStudId().trim());
        }
        if (f2) {
            query.setParameter("studName",
                    "%" + stud.getStudName().trim() + "%");
        }
        if (f3) {
            query.setParameter("deptId", stud.getDeptId().trim());
        }

        return query.list();
    }

}
