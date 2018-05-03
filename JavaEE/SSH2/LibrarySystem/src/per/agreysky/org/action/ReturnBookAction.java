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
    //还书
    public String returnBook() {
        if (lend.getISBN() == null || lend.getISBN().equals("")) {//ISBN号为空
            setMessage("请输入ISBN号！");
            return SUCCESS;
        } else if (lend.getReaderId() == null
                || lend.getReaderId().equals("")) {//借书证号为空
            setMessage("请输入借书证号！");
            return SUCCESS;
        } else if (returnBookService.selectBook(lend.getISBN()) == null) {
            this.setMessage("馆内不存在该图书");
            return SUCCESS;
        } else if (returnBookService
                .selectByReaderId((lend.getReaderId())) == null) {
            setMessage("借书证号不存在！");
            return SUCCESS;
        } else if (returnBookService.selectByISBNAndReaderId(lend.getISBN(),
                lend.getReaderId()) == null) {
            setMessage("您未借阅此书籍！");
            return SUCCESS;
        } else if (returnBookService
                .selectByISBNAndReaderId(lend.getISBN(), lend.getReaderId())
                .isReturnRequest()) {
            setMessage("您已经递交过该书籍归还申请，请等待管理员审核或与管理员联系！");
            return SUCCESS;
        } else {
            Book bo = returnBookService.selectBook(lend.getISBN());
            if (ActionContext.getContext().getSession().get("admin") == null) {
                lend = returnBookService.selectByISBNAndReaderId(
                        lend.getISBN(), lend.getReaderId());
                lend.setReturnRequest(true);
                returnBookService.returnBookRequet(lend);
                setMessage("还书申请登记成功！请前往服务台与管理员确认还书...");
                return SUCCESS;
            } else {
                bo.setSnum(bo.getSnum() + 1);//库存+1
                Student stu = returnBookService
                        .selectByReaderId(lend.getReaderId());
                stu.setNum(stu.getNum() - 1);//借书量-1
                returnBookService.deleteLend(lend.getISBN(), lend.getReaderId(),
                        bo, stu);
            }

            Map request = (Map) ActionContext.getContext().get("request");
            request.put("book", bo);
            setMessage("还书成功！");
            return SUCCESS;
        }

    }

    //预览图书
    public String readBook() {
        if (lend.getISBN() == null || lend.getISBN().equals("")) {//ISBN号为空
            setMessage("请输入ISBN号！");
            return SUCCESS;
        } else if (lend.getReaderId() == null
                || lend.getReaderId().equals("")) {//借书证号为空
            setMessage("请输入借书证号！");
            return SUCCESS;
        } else if (returnBookService.selectBook(lend.getISBN()) == null) {
            this.setMessage("馆内不存在该图书");
            return SUCCESS;
        } else if (returnBookService
                .selectByReaderId((lend.getReaderId())) == null) {
            setMessage("借书证号不存在！");
            return SUCCESS;
        } else if (returnBookService.selectByISBNAndReaderId(lend.getISBN(),
                lend.getReaderId()) == null) {
            setMessage("您未借阅此书籍！");
            return SUCCESS;
        } else {
            Book bo = returnBookService.selectBook(lend.getISBN());
            Map request = (Map) ActionContext.getContext().get("request");
            request.put("book", bo);
            return SUCCESS;
        }
    }
    //跳转到还书页面
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
