package per.agreysky.org.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.org.dao.BaseDAO;
import per.agreysky.org.dao.BookDAO;
import per.agreysky.org.vo.Book;

public class BookDAOImpl extends BaseDAO implements BookDAO {

    //通过ISBN查找图书
    @Override
    public Book selectBook(String ISBN) {
        Book book = null;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Query query = session.createQuery("from Book where ISBN=?");
            query.setParameter(0, ISBN);
            query.setMaxResults(1);
            book = (Book) query.uniqueResult();
            tx.commit();//提交事务
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return book;
    }

    //更新图书
    @Override
    public void updateBook(Book book) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.update(book);//修改操作
            tx.commit();//提交事务
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //添加图书
    @Override
    public void addBook(Book bo) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.save(bo);//保存操作
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

    //删除图书
    @Override
    public void deleteBook(Book book) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
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

    //通过条件查找图书
    @Override
    public ArrayList<Book> findBookByWord(String word, int pageSize,
            int pageNow) {
        ArrayList<Book> list = new ArrayList<Book>();
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
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

    //获取当前指定图书的总数目
    @Override
    public int getTotalSize(String word) {
        int size = 0;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
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
