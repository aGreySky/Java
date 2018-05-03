package per.agreysky.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.bean.Employee;
import per.agreysky.dao.EmployeeDao;

public class EmployeeAction extends ActionSupport implements RequestAware {
    EmployeeDao dao = new EmployeeDao();
    private Employee emp;
    private Map<String, Object> request;

    //员工列表
    public String list() {
        request.put("list", dao.getEmployees());
        return "success";
    }

    //更新成功
    public String update() {
        dao.update(emp);
        return "success";
    }

    //跳转编辑页
    public String edit() {
        request.put("emp", dao.get(emp.getEmployeeId()));
        return "edit";
    }

    //删除员工成功
    public String delete() {
        dao.delete(emp);
        return "success";
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
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

}
