package per.agreysky.org.service.impl;

import java.util.ArrayList;

import per.agreysky.org.dao.BookDAO;
import per.agreysky.org.service.FindService;
import per.agreysky.org.vo.Book;

public class FindServiceImpl implements FindService {
    private BookDAO bookdao;

    public BookDAO getBookdao() {
        return bookdao;
    }
    public void setBookdao(BookDAO bookdao) {
        this.bookdao = bookdao;
    }
    //通过条件查找图书
    @Override
    public ArrayList<Book> findBookByWord(String word, int pageSize,
            int pageNow) {
        return bookdao.findBookByWord(word, pageSize, pageNow);
    }
    //获取当前指定图书的总数目
    @Override
    public int getTotalSize(String word) {
        return bookdao.getTotalSize(word);
    }

}
