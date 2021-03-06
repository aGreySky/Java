package per.agreysky.org.service.impl;

import per.agreysky.org.dao.BookDAO;
import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.service.PhotoService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Student;

public class PhotoServiceImpl implements PhotoService {
    private BookDAO bookdao;
    private StudentDAO studentdao;
    //查找图书
    @Override
    public Book selectBook(String ISBN) {
        return bookdao.selectBook(ISBN);
    }
    //查找学生
    @Override
    public Student selectByReaderId(String readerId) {
        return studentdao.selectByReaderId(readerId);
    }
    public StudentDAO getStudentdao() {
        return studentdao;
    }
    public void setStudentdao(StudentDAO studentdao) {
        this.studentdao = studentdao;
    }
    public BookDAO getBookdao() {
        return bookdao;
    }
    public void setBookdao(BookDAO bookdao) {
        this.bookdao = bookdao;
    }
}
