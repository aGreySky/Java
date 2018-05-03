package per.agreysky.service;
import java.util.List;

import per.agreysky.vo.Book;
import per.agreysky.vo.Lend;
import per.agreysky.vo.Student;
public interface LendService {
    public List selectBook(String readerId, int pageNow, int pageSize);
    public int selectBookSize(String readerId);
    public void addLend(Lend lend, Book book, Student student);
    public Lend selectByBookId(String bookId);
    public Lend selectByBookISBN(String ISBN);
}
