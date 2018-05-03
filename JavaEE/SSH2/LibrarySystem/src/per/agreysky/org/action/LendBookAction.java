package per.agreysky.org.action;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.LendBookService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public class LendBookAction extends ActionSupport {
    private Lend lend;
    private String message;
    private Book book;
    private LendBookService lendBookService;

    //����
    @Transactional
    public String lendBook() {
        if (lend.getISBN() == null || lend.getISBN().equals("")) {//ISBN��Ϊ��
            setMessage("������ISBN�ţ�");
            return SUCCESS;
        } else if (lend.getReaderId() == null
                || lend.getReaderId().equals("")) {//����֤��Ϊ��
            setMessage("���������֤�ţ�");
            return SUCCESS;
        } else if (lendBookService.selectBook(lend.getISBN()) == null) {
            this.setMessage("���ڲ����ڸ�ͼ��");
            return SUCCESS;
        } else if (lendBookService
                .selectByReaderId((lend.getReaderId())) == null) {
            setMessage("����֤�Ų����ڣ�");
            return SUCCESS;
        } else if (lendBookService.selectBook(lend.getISBN()).getSnum() < 1) {
            setMessage("��ͼ��û�п�棡");
            return SUCCESS;
        } else if (lendBookService.selectByISBNAndReaderId(lend.getISBN(),
                lend.getReaderId()) != null) {
            if (!lendBookService
                    .selectByISBNAndReaderId(lend.getISBN(), lend.getReaderId())
                    .isLendConfirm()) {
                setMessage("���Ѿ��ݽ������鼮�������룬��ȴ�����Ա��˻������Ա��ϵ��");
                return SUCCESS;
            } else {
                setMessage("��ͼ���ѱ������ߣ������ظ����ģ�");
                return SUCCESS;
            }
        } else {
            lend.setLtime(new Date());
            Book bo = lendBookService.selectBook(lend.getISBN());
            lend.setBookId(bo.getBookId());
            if (ActionContext.getContext().getSession().get("admin") == null) {
                lend.setLendConfirm(false);
                lendBookService.addStuLend(lend);
                setMessage("��������Ǽǳɹ�����ǰ������̨�����Աȷ�Ͻ���...");
            } else {
                lend.setLendConfirm(true);
                bo.setSnum(bo.getSnum() - 1);//���-1 
                Student stu = lendBookService
                        .selectByReaderId(lend.getReaderId());
                stu.setNum(stu.getNum() + 1);//������+1
                lendBookService.addLend(lend, bo, stu);
                setMessage("����ɹ���");
            }

            Map request = (Map) ActionContext.getContext().get("request");
            request.put("book", bo);

            return SUCCESS;
        }

    }

    //Ԥ��ͼ��
    public String readBook() {
        if (lend.getISBN() == null || lend.getISBN().equals("")) {//ISBN��Ϊ��
            setMessage("������ISBN�ţ�");
            return SUCCESS;
        } else if (lendBookService.selectBook(lend.getISBN()) == null) {
            this.setMessage("���ڲ����ڸ�ͼ��");
            return SUCCESS;
        } else {
            Book bo = lendBookService.selectBook(lend.getISBN());
            Map request = (Map) ActionContext.getContext().get("request");
            request.put("book", bo);
            return SUCCESS;
        }
    }
    //��ת������ҳ��
    public String sendToLendBook() throws IOException {
        book = lendBookService.selectBook(book.getISBN());
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("book", book);
        return SUCCESS;
    }

    public Lend getLend() {
        return lend;
    }

    public void setLend(Lend lend) {
        this.lend = lend;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LendBookService getLendBookService() {
        return lendBookService;
    }

    public void setLendBookService(LendBookService lendBookService) {
        this.lendBookService = lendBookService;
    }

}
