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

    //借书
    @Transactional
    public String lendBook() {
        if (lend.getISBN() == null || lend.getISBN().equals("")) {//ISBN号为空
            setMessage("请输入ISBN号！");
            return SUCCESS;
        } else if (lend.getReaderId() == null
                || lend.getReaderId().equals("")) {//借书证号为空
            setMessage("请输入借书证号！");
            return SUCCESS;
        } else if (lendBookService.selectBook(lend.getISBN()) == null) {
            this.setMessage("馆内不存在该图书");
            return SUCCESS;
        } else if (lendBookService
                .selectByReaderId((lend.getReaderId())) == null) {
            setMessage("借书证号不存在！");
            return SUCCESS;
        } else if (lendBookService.selectBook(lend.getISBN()).getSnum() < 1) {
            setMessage("该图书没有库存！");
            return SUCCESS;
        } else if (lendBookService.selectByISBNAndReaderId(lend.getISBN(),
                lend.getReaderId()) != null) {
            if (!lendBookService
                    .selectByISBNAndReaderId(lend.getISBN(), lend.getReaderId())
                    .isLendConfirm()) {
                setMessage("您已经递交过该书籍借阅申请，请等待管理员审核或与管理员联系！");
                return SUCCESS;
            } else {
                setMessage("该图书已被您借走，请勿重复借阅！");
                return SUCCESS;
            }
        } else {
            lend.setLtime(new Date());
            Book bo = lendBookService.selectBook(lend.getISBN());
            lend.setBookId(bo.getBookId());
            if (ActionContext.getContext().getSession().get("admin") == null) {
                lend.setLendConfirm(false);
                lendBookService.addStuLend(lend);
                setMessage("借书申请登记成功！请前往服务台与管理员确认借书...");
            } else {
                lend.setLendConfirm(true);
                bo.setSnum(bo.getSnum() - 1);//库存-1 
                Student stu = lendBookService
                        .selectByReaderId(lend.getReaderId());
                stu.setNum(stu.getNum() + 1);//借书量+1
                lendBookService.addLend(lend, bo, stu);
                setMessage("借书成功！");
            }

            Map request = (Map) ActionContext.getContext().get("request");
            request.put("book", bo);

            return SUCCESS;
        }

    }

    //预览图书
    public String readBook() {
        if (lend.getISBN() == null || lend.getISBN().equals("")) {//ISBN号为空
            setMessage("请输入ISBN号！");
            return SUCCESS;
        } else if (lendBookService.selectBook(lend.getISBN()) == null) {
            this.setMessage("馆内不存在该图书");
            return SUCCESS;
        } else {
            Book bo = lendBookService.selectBook(lend.getISBN());
            Map request = (Map) ActionContext.getContext().get("request");
            request.put("book", bo);
            return SUCCESS;
        }
    }
    //跳转到借书页面
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
