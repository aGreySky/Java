package per.agreysky.org.service;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public interface LendBookService {
    //����ͼ��
    public Book selectBook(String ISBN);

    //����Ա����ͼ��
    public void addLend(Lend lend, Book bo, Student stu);

    //ͨ������֤�Ų���ѧ��
    public Student selectByReaderId(String readerId);

    //���ҽ�����Ϣ
    public Lend selectByISBNAndReaderId(String ISBN, String readerId);

    //ѧ������ͼ��
    public void addStuLend(Lend lend);

}
