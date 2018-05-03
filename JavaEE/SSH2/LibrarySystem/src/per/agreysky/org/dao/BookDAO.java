package per.agreysky.org.dao;

import java.util.ArrayList;

import per.agreysky.org.vo.Book;

public interface BookDAO {
    //通过ISBN查找图书
    public Book selectBook(String ISBN);
    //添加图书
    public void addBook(Book book);
    //删除图书
    public void deleteBook(Book book);
    //更新图书信息
    public void updateBook(Book book);
    //通过条件查找图书
    public ArrayList<Book> findBookByWord(String word, int pageSize,
            int pageNow);
    //获取当前指定图书的总数目
    public int getTotalSize(String word);
}
