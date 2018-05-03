package per.agreysky.dao;
import java.util.List;

import per.agreysky.vo.Book;
import per.agreysky.vo.Lend;
import per.agreysky.vo.Student;
public interface LendDao {
    //分页查询指定借书证号的读者所借图书的信息
    public List selectBook(String readerId, int pageNow, int pageSize);
    //查询指定借书证号的读者所借图书的总数
    public int selectBookSize(String readerId);
    //借书
    public void addLend(Lend lend, Book book, Student student);
    //根据图书ID查询Lend信息
    public Lend selectByBookId(String bookId);
    //根据图书ISBN查询Lend信息
    public Lend selectByBookISBN(String ISBN);
}
