package per.agreysky.org.dao;

import java.util.ArrayList;
import java.util.List;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public interface LendDAO {
    //查询当前页的图书信息
    public List selectLend(String readerId, int pageNow, int pageSize);
    //查询借书总数量
    public int selectLendSize(String readerId);
    //通过ISBN号和readerId查询图书
    public Lend selectByISBNAndReaderId(String ISBN, String readerId);
    //通过ISBN号查询图书
    public Lend selectByISBN(String ISBN);
    //管理员借阅图书
    public void addLend(Lend lend, Book book, Student student);
    //管理员归还图书
    public void deleteLend(String ISBN, String readerId, Book book,
            Student student);

    //修改图书信息
    public void updateLend(Lend lend);
    //添加图书信息
    void saveLend(Lend lend);

    //查找当前页未确认图书
    ArrayList<Lend> findUnCheckLendAndReturnList(int pageSize, int pageNow);
    //查找未确认图书数量
    int getTotalSize();
    //通过id查找借书信息
    public Lend selectById(Integer id);
    //确认借书
    public void confirmLend(Lend lend, Book bo, Student stu);
    //删除图书
    public void deleteLend(Lend lend);
    //确认还书
    public void confirmReturn(Lend lend, Book bo, Student stu);
}
