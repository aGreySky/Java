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

    //��ѯ��ǰҳ��ͼ����Ϣ
    @Override
    public List selectLend(String readerId, int pageNow, int pageSize) {
        List list = null;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
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

    //��ѯ����������
    @Override
    public int selectLendSize(String readerId) {
        int size = 0;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
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

    //ͨ��ISBN�ź�readerId��ѯͼ��
    @Override
    public Lend selectByISBNAndReaderId(String ISBN, String readerId) {
        Lend lend = null;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
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

    //ͨ��ISBN�Ų�ѯͼ��
    @Override
    public Lend selectByISBN(String ISBN) {
        Lend lend = null;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
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

    //ͨ��id��ѯͼ��
    @Override
    public Lend selectById(Integer id) {
        Lend lend = null;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
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

    //����Ա����ͼ��
    @Override
    public void addLend(Lend lend, Book book, Student student) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.save(lend);//����
            session.update(book);//����ͼ��
            session.update(student);//����ѧ��
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

    //����Աȷ�Ͻ���ͼ��
    @Override
    public void confirmLend(Lend lend, Book book, Student student) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.update(lend);//����
            session.update(book);//����ͼ��
            session.update(student);//����ѧ��
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

    //����Աȷ�Ϲ黹ͼ��
    @Override
    public void confirmReturn(Lend lend, Book book, Student student) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.delete(lend);//����
            session.update(book);//����ͼ��
            session.update(student);//����ѧ��
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

    //���������Ϣ
    @Override
    public void saveLend(Lend lend) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.save(lend);//����
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
    //�޸Ľ�����Ϣ
    @Override
    public void updateLend(Lend lend) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.update(lend);//����
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

    //�黹ͼ��
    @Override
    public void deleteLend(String ISBN, String readerId, Book bo,
            Student student) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            Lend lend = this.selectByISBNAndReaderId(ISBN, readerId);
            session.delete(lend);//ɾ��
            session.update(bo);//����ͼ��
            session.update(student);//����ѧ��
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

    //ɾ��
    @Override
    public void deleteLend(Lend lend) {
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
        try {
            session.delete(lend);//����
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

    //���ҵ�ǰҳδȷ��ͼ��
    @Override
    public ArrayList<Lend> findUnCheckLendAndReturnList(int pageSize,
            int pageNow) {
        ArrayList<Lend> list = new ArrayList<Lend>();
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
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
    //����δȷ��ͼ������
    @Override
    public int getTotalSize() {
        int size = 0;
        Session session = getSession();//��ȡ�Ự
        Transaction tx = session.beginTransaction();//��������
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
