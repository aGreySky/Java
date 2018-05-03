package per.agreysky.org.action;

import java.io.IOException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.ReturnBookService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Student;

public class ReturnBookAction extends ActionSupport {
    private Lend lend;
    private String message;
    private ReturnBookService returnBookService;
    //����
    public String returnBook() {
        if (lend.getISBN() == null || lend.getISBN().equals("")) {//ISBN��Ϊ��
            setMessage("������ISBN�ţ�");
            return SUCCESS;
        } else if (lend.getReaderId() == null
                || lend.getReaderId().equals("")) {//����֤��Ϊ��
            setMessage("���������֤�ţ�");
            return SUCCESS;
        } else if (returnBookService.selectBook(lend.getISBN()) == null) {
            this.setMessage("���ڲ����ڸ�ͼ��");
            return SUCCESS;
        } else if (returnBookService
                .selectByReaderId((lend.getReaderId())) == null) {
            setMessage("����֤�Ų����ڣ�");
            return SUCCESS;
        } else if (returnBookService.selectByISBNAndReaderId(lend.getISBN(),
                lend.getReaderId()) == null) {
            setMessage("��δ���Ĵ��鼮��");
            return SUCCESS;
        } else if (returnBookService
                .selectByISBNAndReaderId(lend.getISBN(), lend.getReaderId())
                .isReturnRequest()) {
            setMessage("���Ѿ��ݽ������鼮�黹���룬��ȴ�����Ա��˻������Ա��ϵ��");
            return SUCCESS;
        } else {
            Book bo = returnBookService.selectBook(lend.getISBN());
            if (ActionContext.getContext().getSession().get("admin") == null) {
                lend = returnBookService.selectByISBNAndReaderId(
                        lend.getISBN(), lend.getReaderId());
                lend.setReturnRequest(true);
                returnBookService.returnBookRequet(lend);
                setMessage("��������Ǽǳɹ�����ǰ������̨�����Աȷ�ϻ���...");
                return SUCCESS;
            } else {
                bo.setSnum(bo.getSnum() + 1);//���+1
                Student stu = returnBookService
                        .selectByReaderId(lend.getReaderId());
                stu.setNum(stu.getNum() - 1);//������-1
                returnBookService.deleteLend(lend.getISBN(), lend.getReaderId(),
                        bo, stu);
            }

            Map request = (Map) ActionContext.getContext().get("request");
            request.put("book", bo);
            setMessage("����ɹ���");
            return SUCCESS;
        }

    }

    //Ԥ��ͼ��
    public String readBook() {
        if (lend.getISBN() == null || lend.getISBN().equals("")) {//ISBN��Ϊ��
            setMessage("������ISBN�ţ�");
            return SUCCESS;
        } else if (lend.getReaderId() == null
                || lend.getReaderId().equals("")) {//����֤��Ϊ��
            setMessage("���������֤�ţ�");
            return SUCCESS;
        } else if (returnBookService.selectBook(lend.getISBN()) == null) {
            this.setMessage("���ڲ����ڸ�ͼ��");
            return SUCCESS;
        } else if (returnBookService
                .selectByReaderId((lend.getReaderId())) == null) {
            setMessage("����֤�Ų����ڣ�");
            return SUCCESS;
        } else if (returnBookService.selectByISBNAndReaderId(lend.getISBN(),
                lend.getReaderId()) == null) {
            setMessage("��δ���Ĵ��鼮��");
            return SUCCESS;
        } else {
            Book bo = returnBookService.selectBook(lend.getISBN());
            Map request = (Map) ActionContext.getContext().get("request");
            request.put("book", bo);
            return SUCCESS;
        }
    }
    //��ת������ҳ��
    public String sendToReturnBook() throws IOException {
        lend = returnBookService.selectByISBNAndReaderId(lend.getISBN(),
                lend.getReaderId());
        Book book = returnBookService.selectBook(lend.getISBN());
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("book", book);
        request.put("lend", lend);
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

    public ReturnBookService getReturnBookService() {
        return returnBookService;
    }

    public void setReturnBookService(ReturnBookService returnBookService) {
        this.returnBookService = returnBookService;
    }

}
