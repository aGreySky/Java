package per.agreysky.org.service;

import java.util.ArrayList;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public interface UnCheckLendAndReturnService {
    //查找当前页未确认图书
    ArrayList<Lend> findUnCheckLendAndReturnList(int pageSize, int pageNow);
    //查找未确认图书数量
    int getTotalSize();
    //通过id查找借书信息
    Lend selectLendById(Integer id);
    //查找图书信息
    Book selectBook(String isbn);
    //查找学生信息
    Student selectByReaderId(String readerId);
    //确认借书
    void confirmLend(Lend lend, Book bo, Student stu);
    //删除借书信息
    void deleteLend(Lend lend);
    //确认还书
    void confirmReturn(Lend lend, Book bo, Student stu);
    //更新借书信息
    void updateLend(Lend lend);

}
