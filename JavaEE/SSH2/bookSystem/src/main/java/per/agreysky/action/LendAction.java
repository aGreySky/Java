package per.agreysky.action;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.service.BookService;
import per.agreysky.service.LendService;
import per.agreysky.service.StudentService;
import per.agreysky.tool.Pager;
import per.agreysky.vo.Book;
import per.agreysky.vo.Lend;
import per.agreysky.vo.Student;
public class LendAction extends ActionSupport {
    private int pageNow = 1; //初始页面为第一页
    private int pageSize = 4; //每页显示4天记录
    private Lend lend;
    private LendService lendservice;//++
    private StudentService studentservice;//++
    private BookService bookservice;//++

    private String message;
    //这里省略上面属性的get和set方法（整理文档时需要添加的）
    public int getPageNow() {
        return pageNow;
    }
    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Lend getLend() {
        return lend;
    }
    public void setLend(Lend lend) {
        this.lend = lend;
    }

    public String getMessage() {
        return this.message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    //属性 lendservice 的 get/set 方法
    public LendService getLendservice() {
        return lendservice;
    }
    public void setLendservice(LendService lendservice) {
        this.lendservice = lendservice;
    }

    //属性 studentservice 的 get/set 方法
    public StudentService getStudentservice() {
        return studentservice;
    }
    public void setStudentservice(StudentService studentservice) {
        this.studentservice = studentservice;
    }

    //属性 bookservice 的 get/set 方法
    public BookService getBookservice() {
        return bookservice;
    }
    public void setBookservice(BookService bookservice) {
        this.bookservice = bookservice;
    }
    //
    //LendDao lendDao=new LendDaoImpl();//	创建lendDao对象
    public String selectAllLend() throws Exception {
        //判断输入的借书证号是否为空，如果为空则设置信息，直接返回
        if (lend.getReaderId() == null || lend.getReaderId().equals("")) {
            this.setMessage("请输入借书证号！");
            return SUCCESS;
        } else if (studentservice.selectStudent(lend.getReaderId()) == null) {//++	改为直接使用业务接口封装的方法
            //调用StudentService中的查询学生的方法，如果为null就表示输入的借书证号不存在
            this.setMessage("不存在该学生！");
            return SUCCESS;
        }
        //调用LendService的查询已借图书方法，查询，这里用到了分页查询
        List list = lendservice.selectBook(lend.getReaderId(),
                this.getPageNow(), this.getPageSize());//++
        //根据当前页及一共多少条记录创建分页的类Pager对象
        Pager page = new Pager(pageNow,
                lendservice.selectBookSize(lend.getReaderId()));//++
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("list", list); //保存查询的记录
        request.put("page", page); //保存分页记录
        request.put("readerId", lend.getReaderId()); //保存借书证号
        return SUCCESS;
    }

    public String lendBook() throws Exception {
        //BookDao bookDao=new BookDaoImpl();
        //如果ISBN为空或者不存在该ISBN的书，就返回到原来的情况，只是多了提示信息
        if (lend.getISBN() == null || lend.getISBN().equals("")
                || bookservice.selectBook(lend.getISBN()) == null
                || (bookservice.selectBook(lend.getISBN()).getSnum()) < 1) {//++
            List list = lendservice.selectBook(lend.getReaderId(),
                    this.getPageNow(), this.getPageSize());//++
            Pager page = new Pager(pageNow,
                    lendservice.selectBookSize(lend.getReaderId()));//++
            Map request = (Map) ActionContext.getContext().get("request");
            request.put("list", list);
            request.put("page", page);
            request.put("readerId", lend.getReaderId());
            setMessage("ISBN不能为空或者不存在该ISBN的图书或者该ISBN的图书没有库存量！");
            return SUCCESS;
        } else if (lend.getBookId() == null || lend.getBookId().equals("")
                || lendservice.selectByBookId(lend.getBookId()) != null) {//++
            //如果输入的图书ID为空或该图书ID已经存在也返回到原来的情况，并给出提示信息
            List list = lendservice.selectBook(lend.getReaderId(),
                    this.getPageNow(), this.getPageSize());//++
            Pager page = new Pager(pageNow,
                    lendservice.selectBookSize(lend.getReaderId()));//++
            Map request = (Map) ActionContext.getContext().get("request");
            request.put("list", list); //原来查出的已借图书
            request.put("page", page); //分页
            request.put("readerId", lend.getReaderId()); //借书证号
            this.setMessage("该图书ID已经存在或图书ID为空！");
            return SUCCESS;
        }
        Lend l = new Lend();
        l.setBookId(lend.getBookId()); //设置图书ID
        l.setISBN(lend.getISBN()); //设置图书ISBN
        l.setReaderId(lend.getReaderId()); //设置借书证号
        l.setLtime(new Date()); //设置借书时间为当前时间
        //lendDao.addLend(l, book, student);          					//调用Dao中方法插入信息
        Book book = bookservice.selectBook(lend.getISBN());//++  	 	//取得该ISBN的图书对象
        book.setSnum(book.getSnum() - 1); //设置库存量-1
        //bookDao.updateBook(book);				  			//修改图书
        //StudentDao studentDao=new StudentDaoImpl();
        Student stu = studentservice.selectStudent(lend.getReaderId());//++
        stu.setNum(stu.getNum() + 1); //设置学生的借书量+1
        lendservice.addLend(l, book, stu);//++
        this.setMessage("借书成功！");
        //studentDao.updateStudent(stu);
        List list = lendservice.selectBook(lend.getReaderId(),
                this.getPageNow(), this.getPageSize());//++
        Pager page = new Pager(pageNow,
                lendservice.selectBookSize(lend.getReaderId()));//++
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("list", list);
        request.put("page", page);
        request.put("readerId", lend.getReaderId());
        return SUCCESS;
    }

}
