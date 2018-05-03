package per.agreysky.dao;

import per.agreysky.vo.Book;

public interface BookDao {
    //����ͼ����Ϣ
    public void addBook(Book book);
    //ɾ��ͼ����Ϣ
    public void deleteBook(String ISBN);
    //�޸�ͼ����Ϣ
    public void updateBook(Book book);
    //��ѯͼ����Ϣ
    public Book selectBook(String ISBN);
}
