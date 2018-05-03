package per.agreysky.service.impl;
import per.agreysky.dao.BookDao;
import per.agreysky.service.BookService;
import per.agreysky.vo.Book;
public class BookServiceImpl implements BookService {
    private BookDao bookDao;
    public BookDao getBookDao() {
        return bookDao;
    }
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
    @Override
    public Book selectBook(String ISBN) {
        return bookDao.selectBook(ISBN);
    }
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }
    @Override
    public void deleteBook(String ISBN) {
        bookDao.deleteBook(ISBN);
    }
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }
}
