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
    //ͨ����������ͼ��
    @Override
    public ArrayList<Book> findBookByWord(String word, int pageSize,
            int pageNow) {
        return bookdao.findBookByWord(word, pageSize, pageNow);
    }
    //��ȡ��ǰָ��ͼ�������Ŀ
    @Override
    public int getTotalSize(String word) {
        return bookdao.getTotalSize(word);
    }

}
