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
    private int pageNow = 1;//��ʼҳ��Ϊ��һҳ
    private int pageSize = 10;//ÿҳ��ʾ10����¼
    private int check;//�Ƿ񳬳�ҳ����Χ
    private LendQueryService lendQueryService;

    //��ѯ�ѽ�ͼ��
    public String selectAllLend() {

        //�ж��û�����Ľ���֤���Ƿ�Ϊ��
        if (lend.getReaderId() == null || lend.getReaderId().equals("")) {
            this.setMessage("���������֤�ţ�");
            return SUCCESS;
        } else if (lendQueryService
                .selectByReaderId(lend.getReaderId()) == null) {
            this.setMessage("�����ڸ�ѧ����");
        }

        //��ѯ��ǰҳ��ͼ��
        List list = lendQueryService.selectLend(lend.getReaderId(),
                this.getPageNow(), this.getPageSize());
        //���ݵ�ǰҳ��һ����������¼������ҳ��Page����
        Page page = new Page(this.getPageNow(),
                lendQueryService.selectLendSize(lend.getReaderId()),
                this.getPageSize());
        if (page.getTotalPage() == 0) {
            setMessage("��ʱû�н���ͼ�飡");
        }
        if (this.getCheck() == 1) {
            setMessage("�Ѿ��ǵ�һҳ��");
        } else if (this.getCheck() == 2) {
            setMessage("�Ѿ������һҳ��");
        }
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("list", list);
        request.put("page", page);
        request.put("readerId", lend.getReaderId());
        return SUCCESS;
    }

    //��ѯ�ѽ�ͼ�顢��ҳ������list��page
    public void selectBookAndSetPage() {
        //��ѯ��ǰҳ��ͼ��
        List list = lendQueryService.selectLend(lend.getReaderId(),
                this.getPageNow(), this.getPageSize());
        //���ݵ�ǰҳ��һ����������¼������ҳ��Page����
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
