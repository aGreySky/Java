package per.agreysky.org.service;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Student;

public interface PhotoService {

    //查找图书
    public Book selectBook(String ISBN);
    //查找学生
    public Student selectByReaderId(String readerId);

}
