package per.agreysky.dao.impl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.dao.BaseDAO;
import per.agreysky.dao.BookDao;
import per.agreysky.vo.Book;
public class BookDaoImpl extends BaseDAO implements BookDao {//++
    //����ͼ����Ϣ
    @Override
    public void addBook(Book book) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSession();//++
            tx = session.beginTransaction();
            session.save(book);
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
    //ɾ��ͼ����Ϣ
    @Override
    public void deleteBook(String ISBN) {
        Session session = null;
        Transaction tx = null;
        try {
            Book book = this.selectBook(ISBN);
            session = getSession();//++
            tx = session.beginTransaction();
            session.delete(book);
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
    //��ѯͼ����Ϣ
    @Override
    public Book selectBook(String ISBN) {
        Session session = null;
        Transaction tx = null;
        Book book = null;
        try {
            session = getSession();//++
            tx = session.beginTransaction();
            Query query = session.createQuery("from Book where ISBN=?");
            query.setParameter(0, ISBN);
            query.setMaxResults(1);
            book = (Book) query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return book;
    }
    //�޸�ͼ����Ϣ
    @Override
    public void updateBook(Book book) {
        Session session = null;
        Transaction tx = null;
        try {
            session = getSession();//++
            tx = session.beginTransaction();
            session.update(book);
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
}
