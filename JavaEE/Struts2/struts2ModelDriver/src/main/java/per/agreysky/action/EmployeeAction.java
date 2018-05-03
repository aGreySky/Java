package per.agreysky.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import per.agreysky.bean.Employee;
import per.agreysky.dao.EmployeeDao;

public class EmployeeAction extends ActionSupport
        implements
            RequestAware,
            ModelDriven<Employee>,
            Preparable {
    EmployeeDao dao = new EmployeeDao();
    private Employee emp;
    private Integer employeeId;
    private Map<String, Object> request;

    //员工列表
    public String list() {
        request.put("list", dao.getEmployees());
        return "success";
    }

    //准备更新
    public void prepareUpdate() {
        System.out.println("prepareUpdate方法正在执行...");
        emp = new Employee();
    }

    //更新成功
    public String update() {
        dao.update(emp);
        return "success";
    }

    //准备编辑
    public void prepareEdit() {
        System.out.println("prepareEdit方法正在执行...");
        emp = dao.get(employeeId);
    }

    //跳转编辑页
    public String edit() {
        return "edit";
    }
    //准备删除
    public void prepareDelete() {
        emp = new Employee();
    }

    //删除员工成功
    public String delete() {
        dao.delete(emp);
        return "success";
    }

    //准备新增
    public void prepareAdd() {
        System.out.println("prepareAdd方法正在执行...");
        emp = new Employee();
    }

    //新增成功
    public String add() {
        dao.save(emp);
        return "success";
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    @Override
    public void prepare() throws Exception {
        System.out.println("prepare方法执行...");
    }

    @Override
    public Employee getModel() {
        System.out.println("getModel方法正在执行...");
        return emp;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
