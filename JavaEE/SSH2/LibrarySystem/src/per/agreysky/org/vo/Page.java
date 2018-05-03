package per.agreysky.org.vo;

public class Page {

    private int pageNow;//当前页数
    private int pageSize;//每页显示多少图书
    private int totalPage;//共有多少页
    private int totalSize;//一共有多少记录
    private boolean hasFirst;//是否有首页
    private boolean hasPre;//是否有前一页
    private boolean hasNext;//是否有下一页
    private boolean hasLast;//是否有最后一页

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
        //一共有多少页的算法
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
        //是否有首页的算法
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
        //是否有前一页的算法
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
        //是否有后一页的算法
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
        //是否有最后一页的算法
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
