package per.agreysky.org.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.LendQueryService;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Page;

public class LendQueryAction extends ActionSupport {
    private Lend lend;
    private String message;
    private int pageNow = 1;//初始页面为第一页
    private int pageSize = 10;//每页显示10条记录
    private int check;//是否超出页数范围
    private LendQueryService lendQueryService;

    //查询已借图书
    public String selectAllLend() {

        //判断用户输入的借书证号是否为空
        if (lend.getReaderId() == null || lend.getReaderId().equals("")) {
            this.setMessage("请输入借书证号！");
            return SUCCESS;
        } else if (lendQueryService
                .selectByReaderId(lend.getReaderId()) == null) {
            this.setMessage("不存在该学生！");
        }

        //查询当前页的图书
        List list = lendQueryService.selectLend(lend.getReaderId(),
                this.getPageNow(), this.getPageSize());
        //根据当前页及一共多少条记录创建分页类Page对象
        Page page = new Page(this.getPageNow(),
                lendQueryService.selectLendSize(lend.getReaderId()),
                this.getPageSize());
        if (page.getTotalPage() == 0) {
            setMessage("暂时没有借阅图书！");
        }
        if (this.getCheck() == 1) {
            setMessage("已经是第一页了");
        } else if (this.getCheck() == 2) {
            setMessage("已经是最后一页了");
        }
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("list", list);
        request.put("page", page);
        request.put("readerId", lend.getReaderId());
        return SUCCESS;
    }

    //查询已借图书、分页并储存list和page
    public void selectBookAndSetPage() {
        //查询当前页的图书
        List list = lendQueryService.selectLend(lend.getReaderId(),
                this.getPageNow(), this.getPageSize());
        //根据当前页及一共多少条记录创建分页类Page对象
        Page page = new Page(this.getPageNow(),
                lendQueryService.selectLendSize(lend.getReaderId()),
                this.getPageSize());
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("list", list);
        request.put("page", page);
        request.put("readerId", lend.getReaderId());
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

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public LendQueryService getLendQueryService() {
        return lendQueryService;
    }

    public void setLendQueryService(LendQueryService lendQueryService) {
        this.lendQueryService = lendQueryService;
    }

}
