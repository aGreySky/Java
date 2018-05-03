package per.agreysky.org.service.impl;

import java.util.ArrayList;

import per.agreysky.org.dao.BookDAO;
import per.agreysky.org.dao.LendDAO;
import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.service.UnCheckLendAndReturnService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public class UnCheckLendAndReturnServiceImpl
        implements
            UnCheckLendAndReturnService {
    private LendDAO lenddao;
    private BookDAO bookdao;
    private StudentDAO studentdao;

    //查找当前页未确认图书
    @Override
    public ArrayList<Lend> findUnCheckLendAndReturnList(int pageSize,
            int pageNow) {
        return lenddao.findUnCheckLendAndReturnList(pageSize, pageNow);
    }
    //查找未确认图书数量
    @Override
    public int getTotalSize() {
        return lenddao.getTotalSize();
    }

    //通过id查找借书信息
    @Override
    public Lend selectLendById(Integer id) {
        return lenddao.selectById(id);
    }
    //查找图书信息
    @Override
    public Book selectBook(String isbn) {
        return bookdao.selectBook(isbn);
    }
    //查找学生信息
    @Override
    public Student selectByReaderId(String readerId) {
        return studentdao.selectByReaderId(readerId);
    }
    //确认借书
    @Override
    public void confirmLend(Lend lend, Book bo, Student stu) {
        lenddao.confirmLend(lend, bo, stu);
    }
    //删除借书信息
    @Override
    public void deleteLend(Lend lend) {
        lenddao.deleteLend(lend);
    }
    //确认还书
    @Override
    public void confirmReturn(Lend lend, Book bo, Student stu) {
        lenddao.confirmReturn(lend, bo, stu);
    }
    //更新借书信息
    @Override
    public void updateLend(Lend lend) {
        lenddao.updateLend(lend);
    }
    public LendDAO getLenddao() {
        return lenddao;
    }
    public void setLenddao(LendDAO lenddao) {
        this.lenddao = lenddao;
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

}
