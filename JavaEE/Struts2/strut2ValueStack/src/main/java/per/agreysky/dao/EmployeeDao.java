package per.agreysky.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.bean.Employee;
import per.agreysky.util.HibernateUtil;

public class EmployeeDao {
    private static Map<Integer, Employee> emps = new LinkedHashMap<Integer, Employee>();

    public List<Employee> getEmployees() {

        ArrayList<Employee> list = new ArrayList<Employee>();
        Session session = HibernateUtil.getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Query query = session.createQuery("from Employee");
            list = (ArrayList<Employee>) query.list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }

        return list;
    }

    public void delete(Employee emp) {
        Session session = HibernateUtil.getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.delete(emp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public void save(Employee emp) {
        Session session = HibernateUtil.getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.save(emp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Employee get(Integer empId) {
        Session session = HibernateUtil.getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        Employee emp = null;
        try {
            Query query = session
                    .createQuery("from Employee where employeeId=?");
            query.setParameter(0, empId);
            query.setMaxResults(1);
            emp = (Employee) query.uniqueResult();
            tx.commit();//提交事务
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }

    public void update(Employee emp) {
        Session session = HibernateUtil.getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.update(emp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

}
