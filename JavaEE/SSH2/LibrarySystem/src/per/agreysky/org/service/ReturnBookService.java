package per.agreysky.org.service;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public interface ReturnBookService {

    //查找图书
    public Book selectBook(String ISBN);

    //查找学生
    public Student selectByReaderId(String readerId);

    //查找借阅信息
    public Lend selectByISBNAndReaderId(String ISBN, String readerId);

    //管理员还书
    public void deleteLend(String ISBN, String readerId, Book bo, Student stu);
    //学生还书
    public void returnBookRequet(Lend lend);

}
