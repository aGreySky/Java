package per.agreysky.org.action;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.UnCheckLendAndReturnService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Lend;
import per.agreysky.org.vo.Page;
import per.agreysky.org.vo.Student;

public class UnCheckLendAndReturnAction extends ActionSupport {
    private String message;
    private Lend lend;
    private boolean confirm;//����Աȷ�Ͻ��
    private int pageNow = 1;//��ҳ
    private int pageSize = 10;//ÿҳ����
    private int totalSize;//����
    private int check;//�Ƿ񳬳�ҳ����Χ
    private UnCheckLendAndReturnService unCheckLendAndReturnService;

    //���ҵ�ǰҳδȷ��ͼ��
    public String findOnePageUnCheckLendAndReturn() {
        if (this.getCheck() == 1) {
            setMessage("�Ѿ��ǵ�һҳ��");
        } else if (this.getCheck() == 2) {
            setMessage("�Ѿ������һҳ��");
        }
        ArrayList<Lend> list = new ArrayList<Lend>();
        list = unCheckLendAndReturnService.findUnCheckLendAndReturnList(
                this.getPageSize(), this.getPageNow());
        Page page = new Page(this.getPageNow(),
                unCheckLendAndReturnService.getTotalSize(), this.getPageSize());
        if (page.getTotalPage() == 0) {
            setMessage("����δȷ�Ͻ軹����Ϣ��");
        }
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("list", list);
        request.put("page", page);
        return SUCCESS;
    }

    //����ȷ��
    @Transactional
    public String lendBookConfirm() {
        lend = unCheckLendAndReturnService.selectLendById(lend.getId());
        if (confirm) {
            lend.setLendConfirm(true);
            Book bo = unCheckLendAndReturnService.selectBook(lend.getISBN());
            bo.setSnum(bo.getSnum() - 1);//���-1 
            Student stu = unCheckLendAndReturnService
                    .selectByReaderId(lend.getReaderId());
            stu.setNum(stu.getNum() + 1);//������+1
            unCheckLendAndReturnService.confirmLend(lend, bo, stu);
        } else {
            unCheckLendAndReturnService.deleteLend(lend);
        }
        return SUCCESS;
    }

    //����ȷ��
    public String returnRequestConfirm() {
        lend = unCheckLendAndReturnService.selectLendById(lend.getId());
        if (confirm) {
            Book bo = unCheckLendAndReturnService.selectBook(lend.getISBN());
            bo.setSnum(bo.getSnum() + 1);//���+1 
            Student stu = unCheckLendAndReturnService
                    .selectByReaderId(lend.getReaderId());
            stu.setNum(stu.getNum() - 1);//������-1
            unCheckLendAndReturnService.confirmReturn(lend, bo, stu);
        } else {
            lend.setReturnRequest(false);
            unCheckLendAndReturnService.updateLend(lend);
        }
        return SUCCESS;
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

    public UnCheckLendAndReturnService getUnCheckLendAndReturnService() {
        return unCheckLendAndReturnService;
    }

    public void setUnCheckLendAndReturnService(
            UnCheckLendAndReturnService unCheckLendAndReturnService) {
        this.unCheckLendAndReturnService = unCheckLendAndReturnService;
    }

    public Lend getLend() {
        return lend;
    }

    public void setLend(Lend lend) {
        this.lend = lend;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

}
