package per.agreysky.org.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import per.agreysky.org.dao.BaseDAO;
import per.agreysky.org.dao.LendDAO;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public class LendDAOImpl extends BaseDAO implements LendDAO {

    //查询当前页的图书信息
    @Override
    public List selectLend(String readerId, int pageNow, int pageSize) {
        List list = null;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Query query = session.createQuery(
                    "select l.bookId,l.ISBN,b.bookName,b.publisher,b.price,l.ltime from Lend as l,Book as b"
                            + " where readerId=? and lendConfirm = 1 and b.ISBN=l.ISBN");
            query.setParameter(0, readerId);
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

    //查询借书总数量
    @Override
    public int selectLendSize(String readerId) {
        int size = 0;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
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

    //通过ISBN号和readerId查询图书
    @Override
    public Lend selectByISBNAndReaderId(String ISBN, String readerId) {
        Lend lend = null;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Query query = session
                    .createQuery("from Lend where ISBN=? and readerId=?");
            query.setParameter(0, ISBN);
            query.setParameter(1, readerId);
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

    //通过ISBN号查询图书
    @Override
    public Lend selectByISBN(String ISBN) {
        Lend lend = null;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
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

    //通过id查询图书
    @Override
    public Lend selectById(Integer id) {
        Lend lend = null;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Query query = session.createQuery("from Lend where id=?");
            query.setParameter(0, id);
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

    //管理员借阅图书
    @Override
    public void addLend(Lend lend, Book book, Student student) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.save(lend);//保存
            session.update(book);//更新图书
            session.update(student);//更新学生
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

    //管理员确认借阅图书
    @Override
    public void confirmLend(Lend lend, Book book, Student student) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.update(lend);//保存
            session.update(book);//更新图书
            session.update(student);//更新学生
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

    //管理员确认归还图书
    @Override
    public void confirmReturn(Lend lend, Book book, Student student) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.delete(lend);//保存
            session.update(book);//更新图书
            session.update(student);//更新学生
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

    //保存借书信息
    @Override
    public void saveLend(Lend lend) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.save(lend);//保存
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
    //修改借书信息
    @Override
    public void updateLend(Lend lend) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.update(lend);//更新
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

    //归还图书
    @Override
    public void deleteLend(String ISBN, String readerId, Book bo,
            Student student) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Lend lend = this.selectByISBNAndReaderId(ISBN, readerId);
            session.delete(lend);//删除
            session.update(bo);//更新图书
            session.update(student);//更新学生
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

    //删除
    @Override
    public void deleteLend(Lend lend) {
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            session.delete(lend);//保存
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

    //查找当前页未确认图书
    @Override
    public ArrayList<Lend> findUnCheckLendAndReturnList(int pageSize,
            int pageNow) {
        ArrayList<Lend> list = new ArrayList<Lend>();
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Query query = session.createQuery(
                    "from Lend where lendConfirm = 0 or returnRequest = 1");
            query.setFirstResult(pageSize * (pageNow - 1));
            query.setMaxResults(pageSize);
            list = (ArrayList<Lend>) query.list();
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
    //查找未确认图书数量
    @Override
    public int getTotalSize() {
        int size = 0;
        Session session = getSession();//获取会话
        Transaction tx = session.beginTransaction();//创建事务
        try {
            Query query = session.createQuery(
                    "from Lend where lendConfirm = 0 or returnRequest = 1");
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
