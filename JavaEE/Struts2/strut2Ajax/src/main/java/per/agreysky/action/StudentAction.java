package per.agreysky.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import per.agreysky.bean.Student;
import per.agreysky.service.StudentActionService;

public class StudentAction extends ActionSupport
        implements
            ModelDriven<Student>,
            Preparable {
    StudentActionService service = new StudentActionService();
    private Student stu;

    //返回全部学生列表
    public String list() {
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("stuList", service.queryAllStudents());//集合key中不能带有“-”
        //        ArrayList list = new ArrayList();
        //        list = (ArrayList) session.get("stuList");
        //        System.out.println(list.size());
        return SUCCESS;
    }

    //准备删除
    public void prepareDelete() {
        stu = new Student();
    }

    //删除学生
    public String delete() {
        System.out.println("prepareDelete方法正在执行...");
        service.delStudent(stu);
        return SUCCESS;
    }
    //准备新增
    public void prepareAdd() {
        System.out.println("prepareAdd方法正在执行...");
        stu = new Student();
    }

    //新增成功
    public String add() {
        service.addStudent(stu);
        return SUCCESS;
    }

    //准备返回部分学生列表
    public void prepareMatchList() {
        stu = new Student();
    }

    //返回部分学生列表
    public String matchList() throws IOException {
        System.out.println(1);
        List<Student> matchList = service.queryStudents(stu);
        HttpServletRequest request = ServletActionContext.getRequest();//只能使用Servlet中的request，ajax才能获取到list
        request.getSession().setAttribute("matchList", matchList);
        //        Map request = (Map) ActionContext.getContext().get("request");
        //        request.put("matchList", service.queryStudents(stu));
        //        ArrayList list = new ArrayList();
        //        list = (ArrayList) request.get("matchList");
        //        System.out.println(list.size());
        HttpServletResponse response = ServletActionContext.getResponse();
        PrintWriter out = response.getWriter();
        out.print("1");
        return NONE;
    }

    public Student getStu() {
        return stu;
    }
    public void setStu(Student stu) {
        this.stu = stu;
    }

    @Override
    public void prepare() throws Exception {
        System.out.println("prepare方法执行...");
    }

    @Override
    public Student getModel() {
        System.out.println("getModel方法正在执行...");
        return stu;
    }

}
