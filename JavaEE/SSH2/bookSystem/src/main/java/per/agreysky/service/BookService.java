package per.agreysky.service;

import per.agreysky.vo.Book;

public interface BookService {
    //��ѯͼ����Ϣ
    public Book selectBook(String ISBN);
    //���ͼ��
    public void addBook(Book book);
    //ɾ��ͼ��
    public void deleteBook(String ISBN);
    //�޸�ͼ��
    public void updateBook(Book book);
}
