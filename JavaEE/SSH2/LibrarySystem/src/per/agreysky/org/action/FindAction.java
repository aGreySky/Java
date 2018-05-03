package per.agreysky.org.action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.FindService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Page;

public class FindAction extends ActionSupport {
    private String word;//检索条件
    private String message;
    private int pageNow = 1;//首页
    private int pageSize = 3;//每页数量
    private int totalSize;//总数
    private int check;//是否超出页数范围
    private FindService findService;

    //查找当前页图书
    public String findOnePageBook() {
        //判断用户输入的信息是否为空
        if (word == null || word.equals("")) {
            this.setMessage("请输入图书信息！");
            return SUCCESS;
        }
        if (this.getCheck() == 1) {
            setMessage("已经是第一页了");
        } else if (this.getCheck() == 2) {
            setMessage("已经是最后一页了");
        }
        ArrayList<Book> list = new ArrayList<Book>();
        list = findService.findBookByWord(word, this.getPageSize(),
                this.getPageNow());
        Page page = new Page(this.getPageNow(), findService.getTotalSize(word),
                this.getPageSize());
        if (page.getTotalPage() == 0) {
            setMessage("暂无此类图书信息！");
        }
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("list", list);
        request.put("page", page);
        request.put("word", word);
        return SUCCESS;
    }

    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
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

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public FindService getFindService() {
        return findService;
    }

    public void setFindService(FindService findService) {
        this.findService = findService;
    }

}
