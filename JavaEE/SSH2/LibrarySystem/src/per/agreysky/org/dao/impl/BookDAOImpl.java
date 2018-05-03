package per.agreysky.org.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.org.dao.BaseDAO;
import per.agreysky.org.dao.BookDAO;
import per.agreysky.org.vo.Book;

public class BookDAOImpl extends BaseDAO implements BookDAO {

    //ͨ��ISBN����ͼ��
    @Override
    public Book selectBook(String ISBN) {
        Book book = null;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            Query query = session.createQuery("from Book where ISBN=?");
            query.setParameter(0, ISBN);
            query.setMaxResults(1);
            book = (Book) query.uniqueResult();
            tx.commit();//�ύ����
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return book;
    }

    //����ͼ��
    @Override
    public void updateBook(Book book) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.update(book);//�޸Ĳ���
            tx.commit();//�ύ����
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //���ͼ��
    @Override
    public void addBook(Book bo) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.save(bo);//�������
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

    //ɾ��ͼ��
    @Override
    public void deleteBook(Book book) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
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

    //ͨ����������ͼ��
    @Override
    public ArrayList<Book> findBookByWord(String word, int pageSize,
            int pageNow) {
        ArrayList<Book> list = new ArrayList<Book>();
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            Query query = session.createQuery(
                    "from Book where ISBN ='" + word + "' OR bookName like '%"
                            + word + "%' " + " OR author like '%" + word + "%' "
                            + " OR publisher like '%" + word + "%' "
                            + " OR summary like '%" + word + "%' ");
            query.setFirstResult(pageSize * (pageNow - 1));
            query.setMaxResults(pageSize);
            list = (ArrayList<Book>) query.list();
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

    //��ȡ��ǰָ��ͼ�������Ŀ
    @Override
    public int getTotalSize(String word) {
        int size = 0;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            Query query = session.createQuery(
                    "from Book where ISBN ='" + word + "' OR bookName like '%"
                            + word + "%' " + " OR author like '%" + word + "%' "
                            + " OR publisher like '%" + word + "%' "
                            + " OR summary like '%" + word + "%' ");
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

}
