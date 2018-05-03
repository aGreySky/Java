package per.agreysky.impl;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.dao.BaseDAO;
import per.agreysky.dao.LendDao;
import per.agreysky.vo.Book;
import per.agreysky.vo.Lend;
import per.agreysky.vo.Student;
public class LendDaoImpl extends BaseDAO implements LendDao {
    @Override
    public List selectBook(String readerId, int pageNow, int pageSize) {
        Session session = null;
        Transaction tx = null;
        List list = null;
        try {
            session = getSession();//++
            tx = session.beginTransaction();
            //查询指定的列的信息
            Query query = session.createQuery(
                    "select l.bookId,l.ISBN,b.bookName,b.publisher,b.price,l.ltime from Lend as l,Book as b where l.readerId=? and b.ISBN=l.ISBN");
            query.setParameter(0, readerId);
            //分页
            query.setFirstResult(pageSize * (pageNow - 1));
            query.setMaxResults(pageSize);
            list = query.list();
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
    @Override
    public int selectBookSize(String readerId) {
        Session session = null;
        Transaction tx = null;
        int size = 0;
        try {
            session = getSession();//++
            tx = session.beginTransaction();
            Query query = session.createQuery("from Lend where readerId=?");
            query.setParameter(0, readerId);
            size = query.list().size();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return size;
    }
    @Override
    public void addLend(Lend lend, Book book, Student student) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSession();//++
            tx = session.beginTransaction();
            session.save(lend); //添加借书信息
            session.update(book); //修改图书信息，图书的库存量-1
            session.update(student); //修改学生信息，学生的借书量+1
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
    @Override
    public Lend selectByBookId(String bookId) {
        Session session = null;
        Transaction tx = null;
        Lend lend = null;
        try {
            session = getSession();//++
            tx = session.beginTransaction();
            lend = (Lend) session.get(Lend.class, bookId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lend;
    }
    @Override
    public Lend selectByBookISBN(String ISBN) {
        Session session = null;
        Transaction tx = null;
        Lend lend = null;
        try {
            session = getSession();//++
            tx = session.beginTransaction();
            Query query = session.createQuery("from Lend where ISBN=?");
            query.setParameter(0, ISBN);
            query.setMaxResults(1);
            lend = (Lend) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lend;
    }
}
