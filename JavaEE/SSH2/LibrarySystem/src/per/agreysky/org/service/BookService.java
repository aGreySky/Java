package per.agreysky.org.service;

import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;

public interface BookService {
    public void addBook(Book bo);//图书添加
    public Book selectBook(String ISBN);//图书查询
    public void deleteBook(Book bo);//图书删除
    public void updateBook(Book bo);//图书修改
    public Lend selectByISBN(String isbn);//借书查找
}
