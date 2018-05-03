package per.agreysky.org.service.impl;

import per.agreysky.org.dao.BookDAO;
import per.agreysky.org.dao.LendDAO;
import per.agreysky.org.service.BookService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;

public class BookServiceImpl implements BookService {
    private BookDAO bookdao;
    private LendDAO lenddao;

    public LendDAO getLenddao() {
        return lenddao;
    }
    public void setLenddao(LendDAO lenddao) {
        this.lenddao = lenddao;
    }
    //借书查找
    @Override
    public Lend selectByISBN(String ISBN) {
        return lenddao.selectByISBN(ISBN);
    }
    //图书添加
    @Override
    public void addBook(Book bo) {
        bookdao.addBook(bo);
    }

    //图书查询
    @Override
    public Book selectBook(String ISBN) {
        return bookdao.selectBook(ISBN);
    }

    //图书删除
    @Override
    public void deleteBook(Book bo) {
        bookdao.deleteBook(bo);
    }

    //图书修改
    @Override
    public void updateBook(Book bo) {
        bookdao.updateBook(bo);
    }

    public BookDAO getBookdao() {
        return bookdao;
    }

    public void setBookdao(BookDAO bookdao) {
        this.bookdao = bookdao;
    }
}