package per.agreysky.org.service;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;

public interface BookService {
    public void addBook(Book bo);//ͼ�����
    public Book selectBook(String ISBN);//ͼ���ѯ
    public void deleteBook(Book bo);//ͼ��ɾ��
    public void updateBook(Book bo);//ͼ���޸�
    public Lend selectByISBN(String isbn);//�������
}
