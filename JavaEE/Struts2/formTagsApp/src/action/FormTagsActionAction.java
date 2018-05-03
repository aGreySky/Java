package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import vo.Book;
import vo.BookList;

public class FormTagsActionAction extends ActionSupport {
    private String province;
    private String city;
    private String author;

    private String[] bName = {"语文", "数学", "英语", "C语言", "科学"};
    private String[] bAuthor = {"张三", "李四", "王五", "赵六", "周七"};

    public String Select() {
        return SUCCESS;
    }

    public String BookList() {
        ArrayList<Book> books = new ArrayList<>();
        for (int i = 0; i < bName.length; i++) {
            Book bo = new Book();
            bo.setName(bName[i]);
            bo.setAuthor(bAuthor[i]);
            books.add(bo);
        }
        BookList bookList = new BookList();
        bookList.setBooks(books);
        Map<String, String> bookMap = new HashMap<String, String>();
        for (int i = 0; i < bookList.getBooks().size(); i++) {
            bookMap.put(bookList.getBooks().get(i).getName(),
                    bookList.getBooks().get(i).getAuthor());
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("bookMap", bookMap);
        return SUCCESS;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
