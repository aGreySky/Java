package per.agreysky.org.service.impl;

import per.agreysky.org.dao.BookDAO;
import per.agreysky.org.dao.LendDAO;
import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.service.LendBookService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public class LendBookServiceImpl implements LendBookService {
    private BookDAO bookdao;
    private StudentDAO studentdao;
    private LendDAO lenddao;

    //查找图书
    @Override
    public Book selectBook(String ISBN) {
        return bookdao.selectBook(ISBN);
    }

    //管理员借阅图书
    @Override
    public void addLend(Lend lend, Book bo, Student stu) {
        lenddao.addLend(lend, bo, stu);
    }

    //通过借书证号查找学生
    @Override
    public Student selectByReaderId(String readerId) {
        return studentdao.selectByReaderId(readerId);
    }

    //查找借阅信息
    @Override
    public Lend selectByISBNAndReaderId(String ISBN, String readerId) {
        return lenddao.selectByISBNAndReaderId(ISBN, readerId);
    }

    //学生借阅图书    
    @Override
    public void addStuLend(Lend lend) {
        lenddao.saveLend(lend);

    }

    public BookDAO getBookdao() {
        return bookdao;
    }

    public void setBookdao(BookDAO bookdao) {
        this.bookdao = bookdao;
    }

    public StudentDAO getStudentdao() {
        return studentdao;
    }

    public void setStudentdao(StudentDAO studentdao) {
        this.studentdao = studentdao;
    }

    public LendDAO getLenddao() {
        return lenddao;
    }

    public void setLenddao(LendDAO lenddao) {
        this.lenddao = lenddao;
    }

}
