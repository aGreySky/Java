package per.agreysky.dao;

import per.agreysky.vo.Book;

public interface BookDao {
    //保存图书信息
    public void addBook(Book book);
    //删除图书信息
    public void deleteBook(String ISBN);
    //修改图书信息
    public void updateBook(Book book);
    //查询图书信息
    public Book selectBook(String ISBN);
}
