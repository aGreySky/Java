package per.agreysky.dao;
import java.util.List;

import per.agreysky.vo.Book;
import per.agreysky.vo.Lend;
import per.agreysky.vo.Student;
public interface LendDao {
    //��ҳ��ѯָ������֤�ŵĶ�������ͼ�����Ϣ
    public List selectBook(String readerId, int pageNow, int pageSize);
    //��ѯָ������֤�ŵĶ�������ͼ�������
    public int selectBookSize(String readerId);
    //����
    public void addLend(Lend lend, Book book, Student student);
    //����ͼ��ID��ѯLend��Ϣ
    public Lend selectByBookId(String bookId);
    //����ͼ��ISBN��ѯLend��Ϣ
    public Lend selectByBookISBN(String ISBN);
}
