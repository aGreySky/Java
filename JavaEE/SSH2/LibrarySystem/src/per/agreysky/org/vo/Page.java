package per.agreysky.org.vo;

public class Page {

    private int pageNow;//��ǰҳ��
    private int pageSize;//ÿҳ��ʾ����ͼ��
    private int totalPage;//���ж���ҳ
    private int totalSize;//һ���ж��ټ�¼
    private boolean hasFirst;//�Ƿ�����ҳ
    private boolean hasPre;//�Ƿ���ǰһҳ
    private boolean hasNext;//�Ƿ�����һҳ
    private boolean hasLast;//�Ƿ������һҳ

    public Page(int pageNow, int totalSize, int pageSize) {
        this.pageNow = pageNow;
        this.totalSize = totalSize;
        this.pageSize = pageSize;
        this.totalPage = totalSize / pageSize;
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
    public int getTotalPage() {
        //һ���ж���ҳ���㷨
        totalPage = getTotalSize() / getPageSize();
        if (totalSize % pageSize != 0) {
            totalPage++;
        }
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalSize() {
        return totalSize;
    }
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
    public boolean isHasFirst() {
        //�Ƿ�����ҳ���㷨
        if (pageNow <= 1) {
            return false;
        } else {
            return true;
        }
    }
    public void setHasFirst(boolean hasFirst) {
        this.hasFirst = hasFirst;
    }
    public boolean isHasPre() {
        //�Ƿ���ǰһҳ���㷨
        if (this.isHasFirst()) {
            return true;
        } else {
            return false;
        }
    }
    public void setHasPre(boolean hasPre) {
        this.hasPre = hasPre;
    }
    public boolean isHasNext() {
        //�Ƿ��к�һҳ���㷨
        if (this.isHasLast()) {
            return true;
        } else {
            return false;
        }
    }
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
    public boolean isHasLast() {
        //�Ƿ������һҳ���㷨
        if (pageNow >= this.getTotalPage()) {
            return false;
        } else {
            return true;
        }
    }
    public void setHasLast(boolean hasLast) {
        this.hasLast = hasLast;
    }

}
