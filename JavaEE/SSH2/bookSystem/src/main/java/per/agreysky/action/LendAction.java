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
    private int pageNow = 1; //��ʼҳ��Ϊ��һҳ
    private int pageSize = 4; //ÿҳ��ʾ4���¼
    private Lend lend;
    private LendService lendservice;//++
    private StudentService studentservice;//++
    private BookService bookservice;//++

    private String message;
    //����ʡ���������Ե�get��set�����������ĵ�ʱ��Ҫ��ӵģ�
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

    //���� lendservice �� get/set ����
    public LendService getLendservice() {
        return lendservice;
    }
    public void setLendservice(LendService lendservice) {
        this.lendservice = lendservice;
    }

    //���� studentservice �� get/set ����
    public StudentService getStudentservice() {
        return studentservice;
    }
    public void setStudentservice(StudentService studentservice) {
        this.studentservice = studentservice;
    }

    //���� bookservice �� get/set ����
    public BookService getBookservice() {
        return bookservice;
    }
    public void setBookservice(BookService bookservice) {
        this.bookservice = bookservice;
    }
    //
    //LendDao lendDao=new LendDaoImpl();//	����lendDao����
    public String selectAllLend() throws Exception {
        //�ж�����Ľ���֤���Ƿ�Ϊ�գ����Ϊ����������Ϣ��ֱ�ӷ���
        if (lend.getReaderId() == null || lend.getReaderId().equals("")) {
            this.setMessage("���������֤�ţ�");
            return SUCCESS;
        } else if (studentservice.selectStudent(lend.getReaderId()) == null) {//++	��Ϊֱ��ʹ��ҵ��ӿڷ�װ�ķ���
            //����StudentService�еĲ�ѯѧ���ķ��������Ϊnull�ͱ�ʾ����Ľ���֤�Ų�����
            this.setMessage("�����ڸ�ѧ����");
            return SUCCESS;
        }
        //����LendService�Ĳ�ѯ�ѽ�ͼ�鷽������ѯ�������õ��˷�ҳ��ѯ
        List list = lendservice.selectBook(lend.getReaderId(),
                this.getPageNow(), this.getPageSize());//++
        //���ݵ�ǰҳ��һ����������¼������ҳ����Pager����
        Pager page = new Pager(pageNow,
                lendservice.selectBookSize(lend.getReaderId()));//++
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("list", list); //�����ѯ�ļ�¼
        request.put("page", page); //�����ҳ��¼
        request.put("readerId", lend.getReaderId()); //�������֤��
        return SUCCESS;
    }

    public String lendBook() throws Exception {
        //BookDao bookDao=new BookDaoImpl();
        //���ISBNΪ�ջ��߲����ڸ�ISBN���飬�ͷ��ص�ԭ���������ֻ�Ƕ�����ʾ��Ϣ
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
            setMessage("ISBN����Ϊ�ջ��߲����ڸ�ISBN��ͼ����߸�ISBN��ͼ��û�п������");
            return SUCCESS;
        } else if (lend.getBookId() == null || lend.getBookId().equals("")
                || lendservice.selectByBookId(lend.getBookId()) != null) {//++
            //��������ͼ��IDΪ�ջ��ͼ��ID�Ѿ�����Ҳ���ص�ԭ�����������������ʾ��Ϣ
            List list = lendservice.selectBook(lend.getReaderId(),
                    this.getPageNow(), this.getPageSize());//++
            Pager page = new Pager(pageNow,
                    lendservice.selectBookSize(lend.getReaderId()));//++
            Map request = (Map) ActionContext.getContext().get("request");
            request.put("list", list); //ԭ��������ѽ�ͼ��
            request.put("page", page); //��ҳ
            request.put("readerId", lend.getReaderId()); //����֤��
            this.setMessage("��ͼ��ID�Ѿ����ڻ�ͼ��IDΪ�գ�");
            return SUCCESS;
        }
        Lend l = new Lend();
        l.setBookId(lend.getBookId()); //����ͼ��ID
        l.setISBN(lend.getISBN()); //����ͼ��ISBN
        l.setReaderId(lend.getReaderId()); //���ý���֤��
        l.setLtime(new Date()); //���ý���ʱ��Ϊ��ǰʱ��
        //lendDao.addLend(l, book, student);          					//����Dao�з���������Ϣ
        Book book = bookservice.selectBook(lend.getISBN());//++  	 	//ȡ�ø�ISBN��ͼ�����
        book.setSnum(book.getSnum() - 1); //���ÿ����-1
        //bookDao.updateBook(book);				  			//�޸�ͼ��
        //StudentDao studentDao=new StudentDaoImpl();
        Student stu = studentservice.selectStudent(lend.getReaderId());//++
        stu.setNum(stu.getNum() + 1); //����ѧ���Ľ�����+1
        lendservice.addLend(l, book, stu);//++
        this.setMessage("����ɹ���");
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
