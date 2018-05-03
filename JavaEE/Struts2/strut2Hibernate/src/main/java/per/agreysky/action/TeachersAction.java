package per.agreysky.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import per.agreysky.bean.Teachers;
import per.agreysky.dao.TeachersDao;

public class TeachersAction extends ActionSupport
        implements
            RequestAware,
            ModelDriven<Teachers>,
            Preparable {
    TeachersDao dao = new TeachersDao();
    private Teachers t;
    private Long id;
    private Map<String, Object> request;

    //老师列表
    public String list() {
        request.put("list", dao.getTeachers());
        return "success";
    }

    //准备更新
    public void prepareUpdate() {
        System.out.println("prepareUpdate方法正在执行...");
        t = new Teachers();
    }

    //更新成功
    public String update() {
        dao.update(t);
        return "success";
    }

    //准备编辑
    public void prepareEdit() {
        System.out.println("prepareEdit方法正在执行...");
        t = dao.get(id);
    }

    //跳转编辑页
    public String edit() {
        return "edit";
    }
    //准备删除
    public void prepareDelete() {
        t = new Teachers();
    }

    //删除成功
    public String delete() {
        dao.delete(t);
        return "success";
    }

    //准备新增
    public void prepareAdd() {
        System.out.println("prepareAdd方法正在执行...");
        t = new Teachers();
    }

    //新增成功
    public String add() {
        dao.save(t);
        return "success";
    }

    @Override
    public void prepare() throws Exception {
        System.out.println("prepare方法执行...");
    }

    @Override
    public Teachers getModel() {
        System.out.println("getModel方法正在执行...");
        return t;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public Teachers getT() {
        return t;
    }

    public void setT(Teachers t) {
        this.t = t;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
