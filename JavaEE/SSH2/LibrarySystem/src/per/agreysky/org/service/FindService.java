package per.agreysky.org.service;

import java.util.ArrayList;

import per.agreysky.org.vo.Book;

public interface FindService {
    //通过条件查找图书
    public ArrayList<Book> findBookByWord(String word, int pageSize,
            int pageNow);
    //获取当前指定图书的总数目
    public int getTotalSize(String word);

}
