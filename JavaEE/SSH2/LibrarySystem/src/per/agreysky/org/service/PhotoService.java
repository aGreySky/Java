package per.agreysky.org.service;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Student;

public interface PhotoService {

    //����ͼ��
    public Book selectBook(String ISBN);
    //����ѧ��
    public Student selectByReaderId(String readerId);

}
