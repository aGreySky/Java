package per.agreysky.org.service;

import java.util.ArrayList;

import per.agreysky.org.vo.Book;

public interface FindService {
    //ͨ����������ͼ��
    public ArrayList<Book> findBookByWord(String word, int pageSize,
            int pageNow);
    //��ȡ��ǰָ��ͼ�������Ŀ
    public int getTotalSize(String word);

}
