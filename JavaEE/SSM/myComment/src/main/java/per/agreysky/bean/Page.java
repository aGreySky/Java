package per.agreysky.bean;

import lombok.Data;

@Data
public class Page {

    //总条数
    private int totalNumber;

    // 当前页数
    private int currentPage;

    // 总页数
    private int totalPage;

    // 每页显示条数
    private int pageNumber;

    public Page() {
        this.currentPage = 1;
        this.pageNumber = 5;
    }
}
