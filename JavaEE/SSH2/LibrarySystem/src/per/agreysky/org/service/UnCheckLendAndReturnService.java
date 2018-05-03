package per.agreysky.org.service;

import java.util.ArrayList;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public interface UnCheckLendAndReturnService {
    //���ҵ�ǰҳδȷ��ͼ��
    ArrayList<Lend> findUnCheckLendAndReturnList(int pageSize, int pageNow);
    //����δȷ��ͼ������
    int getTotalSize();
    //ͨ��id���ҽ�����Ϣ
    Lend selectLendById(Integer id);
    //����ͼ����Ϣ
    Book selectBook(String isbn);
    //����ѧ����Ϣ
    Student selectByReaderId(String readerId);
    //ȷ�Ͻ���
    void confirmLend(Lend lend, Book bo, Student stu);
    //ɾ��������Ϣ
    void deleteLend(Lend lend);
    //ȷ�ϻ���
    void confirmReturn(Lend lend, Book bo, Student stu);
    //���½�����Ϣ
    void updateLend(Lend lend);

}
