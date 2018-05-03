package per.agreysky.service;

import per.agreysky.vo.Book;

public interface BookService {
    //查询图书信息
    public Book selectBook(String ISBN);
    //添加图书
    public void addBook(Book book);
    //删除图书
    public void deleteBook(String ISBN);
    //修改图书
    public void updateBook(Book book);
}
