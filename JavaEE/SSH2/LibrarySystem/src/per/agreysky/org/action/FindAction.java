package per.agreysky.org.action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.FindService;
import per.agreysky.org.vo.Book;
import per.agreysky.org.vo.Page;

public class FindAction extends ActionSupport {
    private String word;//��������
    private String message;
    private int pageNow = 1;//��ҳ
    private int pageSize = 3;//ÿҳ����
    private int totalSize;//����
    private int check;//�Ƿ񳬳�ҳ����Χ
    private FindService findService;

    //���ҵ�ǰҳͼ��
    public String findOnePageBook() {
        //�ж��û��������Ϣ�Ƿ�Ϊ��
        if (word == null || word.equals("")) {
            this.setMessage("������ͼ����Ϣ��");
            return SUCCESS;
        }
        if (this.getCheck() == 1) {
            setMessage("�Ѿ��ǵ�һҳ��");
        } else if (this.getCheck() == 2) {
            setMessage("�Ѿ������һҳ��");
        }
        ArrayList<Book> list = new ArrayList<Book>();
        list = findService.findBookByWord(word, this.getPageSize(),
                this.getPageNow());
        Page page = new Page(this.getPageNow(), findService.getTotalSize(word),
                this.getPageSize());
        if (page.getTotalPage() == 0) {
            setMessage("���޴���ͼ����Ϣ��");
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
