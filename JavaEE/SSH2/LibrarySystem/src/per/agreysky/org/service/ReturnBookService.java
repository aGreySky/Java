package per.agreysky.org.service;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public interface ReturnBookService {

    //����ͼ��
    public Book selectBook(String ISBN);

    //����ѧ��
    public Student selectByReaderId(String readerId);

    //���ҽ�����Ϣ
    public Lend selectByISBNAndReaderId(String ISBN, String readerId);

    //����Ա����
    public void deleteLend(String ISBN, String readerId, Book bo, Student stu);
    //ѧ������
    public void returnBookRequet(Lend lend);

}
