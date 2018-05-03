package per.agreysky.org.service;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public interface LendBookService {
    //查找图书
    public Book selectBook(String ISBN);

    //管理员借阅图书
    public void addLend(Lend lend, Book bo, Student stu);

    //通过借书证号查找学生
    public Student selectByReaderId(String readerId);

    //查找借阅信息
    public Lend selectByISBNAndReaderId(String ISBN, String readerId);

    //学生借阅图书
    public void addStuLend(Lend lend);

}
