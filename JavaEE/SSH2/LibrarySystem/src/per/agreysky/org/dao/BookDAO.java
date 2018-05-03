package per.agreysky.org.dao;

import java.util.ArrayList;

import per.agreysky.org.vo.Book;

public interface BookDAO {
    //ͨ��ISBN����ͼ��
    public Book selectBook(String ISBN);
    //���ͼ��
    public void addBook(Book book);
    //ɾ��ͼ��
    public void deleteBook(Book book);
    //����ͼ����Ϣ
    public void updateBook(Book book);
    //ͨ����������ͼ��
    public ArrayList<Book> findBookByWord(String word, int pageSize,
            int pageNow);
    //��ȡ��ǰָ��ͼ�������Ŀ
    public int getTotalSize(String word);
}
