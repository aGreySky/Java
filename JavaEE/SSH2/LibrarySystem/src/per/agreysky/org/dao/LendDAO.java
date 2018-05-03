package per.agreysky.org.dao;

import java.util.ArrayList;
import java.util.List;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public interface LendDAO {
    //��ѯ��ǰҳ��ͼ����Ϣ
    public List selectLend(String readerId, int pageNow, int pageSize);
    //��ѯ����������
    public int selectLendSize(String readerId);
    //ͨ��ISBN�ź�readerId��ѯͼ��
    public Lend selectByISBNAndReaderId(String ISBN, String readerId);
    //ͨ��ISBN�Ų�ѯͼ��
    public Lend selectByISBN(String ISBN);
    //����Ա����ͼ��
    public void addLend(Lend lend, Book book, Student student);
    //����Ա�黹ͼ��
    public void deleteLend(String ISBN, String readerId, Book book,
            Student student);

    //�޸�ͼ����Ϣ
    public void updateLend(Lend lend);
    //���ͼ����Ϣ
    void saveLend(Lend lend);

    //���ҵ�ǰҳδȷ��ͼ��
    ArrayList<Lend> findUnCheckLendAndReturnList(int pageSize, int pageNow);
    //����δȷ��ͼ������
    int getTotalSize();
    //ͨ��id���ҽ�����Ϣ
    public Lend selectById(Integer id);
    //ȷ�Ͻ���
    public void confirmLend(Lend lend, Book bo, Student stu);
    //ɾ��ͼ��
    public void deleteLend(Lend lend);
    //ȷ�ϻ���
    public void confirmReturn(Lend lend, Book bo, Student stu);
}
