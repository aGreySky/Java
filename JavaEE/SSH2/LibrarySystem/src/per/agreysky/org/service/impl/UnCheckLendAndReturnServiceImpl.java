package per.agreysky.org.service.impl;

import java.util.ArrayList;

import per.agreysky.org.dao.BookDAO;
import per.agreysky.org.dao.LendDAO;
import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.service.UnCheckLendAndReturnService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public class UnCheckLendAndReturnServiceImpl
        implements
            UnCheckLendAndReturnService {
    private LendDAO lenddao;
    private BookDAO bookdao;
    private StudentDAO studentdao;

    //���ҵ�ǰҳδȷ��ͼ��
    @Override
    public ArrayList<Lend> findUnCheckLendAndReturnList(int pageSize,
            int pageNow) {
        return lenddao.findUnCheckLendAndReturnList(pageSize, pageNow);
    }
    //����δȷ��ͼ������
    @Override
    public int getTotalSize() {
        return lenddao.getTotalSize();
    }

    //ͨ��id���ҽ�����Ϣ
    @Override
    public Lend selectLendById(Integer id) {
        return lenddao.selectById(id);
    }
    //����ͼ����Ϣ
    @Override
    public Book selectBook(String isbn) {
        return bookdao.selectBook(isbn);
    }
    //����ѧ����Ϣ
    @Override
    public Student selectByReaderId(String readerId) {
        return studentdao.selectByReaderId(readerId);
    }
    //ȷ�Ͻ���
    @Override
    public void confirmLend(Lend lend, Book bo, Student stu) {
        lenddao.confirmLend(lend, bo, stu);
    }
    //ɾ��������Ϣ
    @Override
    public void deleteLend(Lend lend) {
        lenddao.deleteLend(lend);
    }
    //ȷ�ϻ���
    @Override
    public void confirmReturn(Lend lend, Book bo, Student stu) {
        lenddao.confirmReturn(lend, bo, stu);
    }
    //���½�����Ϣ
    @Override
    public void updateLend(Lend lend) {
        lenddao.updateLend(lend);
    }
    public LendDAO getLenddao() {
        return lenddao;
    }
    public void setLenddao(LendDAO lenddao) {
        this.lenddao = lenddao;
    }
    public BookDAO getBookdao() {
        return bookdao;
    }
    public void setBookdao(BookDAO bookdao) {
        this.bookdao = bookdao;
    }
    public StudentDAO getStudentdao() {
        return studentdao;
    }
    public void setStudentdao(StudentDAO studentdao) {
        this.studentdao = studentdao;
    }

}
