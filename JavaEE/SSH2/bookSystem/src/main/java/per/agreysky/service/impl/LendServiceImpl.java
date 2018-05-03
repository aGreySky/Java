package per.agreysky.service.impl;
import java.util.List;

import per.agreysky.dao.LendDao;
import per.agreysky.service.LendService;
import per.agreysky.vo.Book;
import per.agreysky.vo.Lend;
import per.agreysky.vo.Student;
public class LendServiceImpl implements LendService {
    private LendDao lendDao;
    @Override
    public void addLend(Lend lend, Book book, Student student) {
        lendDao.addLend(lend, book, student);
    }
    @Override
    public List selectBook(String readerId, int pageNow, int pageSize) {
        return lendDao.selectBook(readerId, pageNow, pageSize);
    }
    @Override
    public int selectBookSize(String readerId) {
        return lendDao.selectBookSize(readerId);
    }
    @Override
    public Lend selectByBookId(String bookId) {
        return lendDao.selectByBookId(bookId);
    }
    public LendDao getLendDao() {
        return lendDao;
    }
    public void setLendDao(LendDao lendDao) {
        this.lendDao = lendDao;
    }
    @Override
    public Lend selectByBookISBN(String ISBN) {
        return lendDao.selectByBookISBN(ISBN);
    }
}
