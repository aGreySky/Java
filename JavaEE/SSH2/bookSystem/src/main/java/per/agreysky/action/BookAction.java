package per.agreysky.action;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.service.BookService;
import per.agreysky.service.LendService;
import per.agreysky.vo.Book;
public class BookAction extends ActionSupport {
    private String message;
    private File photo;
    private Book book;
    private BookService bookservice;
    private LendService lendservice;

    //省略上面属性的get和set方法（整理文档时需要添加的）
    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public File getPhoto() {
        return photo;
    }
    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    //属性 bookservice 的 get/set 方法
    public BookService getBookservice() {
        return bookservice;
    }
    public void setBookservice(BookService bookservice) {
        this.bookservice = bookservice;
    }

    //属性 lendservice 的 get/set 方法
    public LendService getLendservice() {
        return lendservice;
    }
    public void setLendservice(LendService lendservice) {
        this.lendservice = lendservice;
    }
    //
    //BookDao bookDao=new BookDaoImpl();
    public String addBook() throws Exception {
        Book bo = bookservice.selectBook(book.getISBN());//++
        if (bo != null) { //判断要添加的图书是否已经存在
            this.setMessage("ISBN已经存在！");
            return SUCCESS;
        }
        Book b = new Book();
        b.setISBN(book.getISBN());
        b.setBookName(book.getBookName());
        b.setAuthor(book.getAuthor());
        b.setPublisher(book.getPublisher());
        b.setPrice(book.getPrice());
        b.setCnum(book.getCnum());
        b.setSnum(book.getCnum());
        b.setSummary(book.getSummary());
        if (this.getPhoto() != null) {
            FileInputStream fis = new FileInputStream(this.getPhoto());
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            b.setPhoto(buffer);
        }
        bookservice.addBook(b);
        this.setMessage("添加成功！");
        return SUCCESS;
    }

    public String deleteBook() throws Exception {
        //判断要删除的ISBN是否存在，即是否存在该书籍
        if (book.getISBN() == null || book.getISBN().equals("")) {
            this.setMessage("请输入ISBN号");
            return SUCCESS;
        }
        Book bo = bookservice.selectBook(book.getISBN());//++
        if (bo == null) { //首先判断是否存在该图书
            this.setMessage("要删除的图书不存在！");
            return SUCCESS;
        } else if (lendservice.selectByBookISBN(book.getISBN()) != null) {//++
            this.setMessage("该图书已经被借出,故不能删除图书信息！");
            return SUCCESS;
        }
        bookservice.deleteBook(book.getISBN());//++
        this.setMessage("删除成功！");
        return SUCCESS;
    }

    public String selectBook() throws Exception {
        Book onebook = bookservice.selectBook(book.getISBN());//++
        if (onebook == null) {
            this.setMessage("不存在该图书！");
            return SUCCESS;
        }
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("onebook", onebook);
        return SUCCESS;
    }

    public String getImage() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        String ISBN = book.getISBN();
        Book b = bookservice.selectBook(ISBN);//++
        byte[] photo = b.getPhoto();
        response.setContentType("image/jpeg");
        ServletOutputStream os = response.getOutputStream();
        if (photo != null && photo.length != 0) {
            for (int i = 0; i < photo.length; i++) {
                os.write(photo[i]);
            }
            os.flush();
        }
        return NONE;
    }

    public String updateBook() throws Exception {
        Book b = bookservice.selectBook(book.getISBN());//++
        if (b == null) {
            this.setMessage("要修改的图书不存在,请先查看是否存在该图书！");
            return SUCCESS;
        }
        //b.setISBN(book.getISBN());
        b.setBookName(book.getBookName());
        b.setAuthor(book.getAuthor());
        b.setPublisher(book.getPublisher());
        b.setPrice(book.getPrice());
        b.setCnum(book.getCnum());
        b.setSnum(book.getSnum());
        b.setSummary(book.getSummary());
        if (this.getPhoto() != null) {
            FileInputStream fis = new FileInputStream(this.getPhoto());
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            b.setPhoto(buffer);
        }
        bookservice.updateBook(b);
        this.setMessage("修改成功！");
        return SUCCESS;
    }
}
