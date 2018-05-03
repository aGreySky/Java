package per.agreysky.org.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.BookService;
import per.agreysky.org.vo.Book;

public class BookAction extends ActionSupport {
    private String message;
    private File photo;
    private Book book;
    private BookService bookService;

    //图书添加
    public String addBook() throws IOException {
        Book bo = bookService.selectBook(book.getISBN());
        if (bo != null) {
            this.setMessage("该图书已经上架，请勿重复添加");
            return SUCCESS;
        } else {
            bo = new Book();
            bo.setISBN(book.getISBN());
            bo.setBookName(book.getBookName());
            bo.setAuthor(book.getAuthor());
            bo.setPublisher(book.getPublisher());
            bo.setPrice(book.getPrice());
            bo.setCnum(book.getCnum());
            bo.setSnum(book.getSnum());
            bo.setSummary(book.getSummary());
            if (this.getPhoto() != null) {
                FileInputStream fis = new FileInputStream(this.getPhoto());
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                bo.setPhoto(buffer);
            }
            bookService.addBook(bo);
            Map request = (Map) ActionContext.getContext().get("request");
            request.put("onebook", bo);
            this.setMessage("添加成功！");
            return SUCCESS;
        }
    }

    //图书删除
    public String deleteBook() {
        if (book.getISBN().equals("")) {
            this.setMessage("请输入ISBN号!");
            return SUCCESS;
        } else if (bookService.selectByISBN(book.getISBN()) != null) {
            this.setMessage("该图书已经借出，不能删除！");
            return SUCCESS;
        } else if (bookService.selectBook(book.getISBN()) == null) {
            this.setMessage("馆内不存在该图书");
            return SUCCESS;
        } else {
            Book bo = bookService.selectBook(book.getISBN());
            bookService.deleteBook(bo);
            this.setMessage("删除成功！");
            return SUCCESS;
        }
    }

    //图书查询
    public String selectBook() {
        if (book.getISBN().equals("")) {
            this.setMessage("请输入ISBN号!");
            return SUCCESS;
        } else {
            Book onebook = bookService.selectBook(book.getISBN());
            if (onebook == null) {
                this.setMessage("该图书不存在!");
                return SUCCESS;
            } else {
                Map request = (Map) ActionContext.getContext().get("request");
                request.put("onebook", onebook);
                return SUCCESS;
            }
        }
    }

    //图书修改
    public String updateBook() throws Exception {
        if (book.getISBN().equals("")) {
            this.setMessage("请输入ISBN号!");
            return SUCCESS;
        } else {
            Book bo = bookService.selectBook(book.getISBN());
            if (bo == null) {
                this.setMessage("该图书不存在！");
                return SUCCESS;
            } else {
                bo.setISBN(book.getISBN());
                bo.setBookName(book.getBookName());
                bo.setAuthor(book.getAuthor());
                bo.setPublisher(book.getPublisher());
                bo.setPrice(book.getPrice());
                bo.setCnum(book.getCnum());
                bo.setSnum(book.getSnum());
                bo.setSummary(book.getSummary());
                if (this.getPhoto() != null) {
                    FileInputStream fis = new FileInputStream(this.getPhoto());
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    bo.setPhoto(buffer);
                }
                bookService.updateBook(bo);
                this.setMessage("修改成功！");
                Map request = (Map) ActionContext.getContext().get("request");
                request.put("onebook", bo);
                return SUCCESS;
            }
        }
    }

    public String getMessage() {
        return message;
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

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

}
