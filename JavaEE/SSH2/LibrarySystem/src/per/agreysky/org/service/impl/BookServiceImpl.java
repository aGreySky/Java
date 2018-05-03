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
    //�������
    @Override
    public Lend selectByISBN(String ISBN) {
        return lenddao.selectByISBN(ISBN);
    }
    //ͼ�����
    @Override
    public void addBook(Book bo) {
        bookdao.addBook(bo);
    }

    //ͼ���ѯ
    @Override
    public Book selectBook(String ISBN) {
        return bookdao.selectBook(ISBN);
    }

    //ͼ��ɾ��
    @Override
    public void deleteBook(Book bo) {
        bookdao.deleteBook(bo);
    }

    //ͼ���޸�
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