package entity;

import java.util.ArrayList;
import java.util.List;

public class Pages {
    public ArrayList<Items> list = null;
    private int recordCount = 0;//保存商品总数的变量
    private int pagesize = 1;//保存每页显示的记录数的变量
    private int maxPage = 0;//保存最大页数的变量
    private int Page = 1;//当前页数

    public void setPage(int page) {
        Page = page;
    }
    public int getPage() {
        return Page;
    }
    public List<Items> getList() {
        return list;
    }
    public void setList(ArrayList<Items> list) {
        this.list = list;
    }
    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
    public int getRecordCount() {
        return recordCount;
    }
    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
    public int getPagesize() {
        return pagesize;
    }
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    public int getRecordSize() {//获取商品个数
        return recordCount;
    }
    public int getMaxPage() {//获取最大页数
        int maxPage = (recordCount % pagesize == 0)
                ? (recordCount % pagesize)
                : (recordCount / pagesize + 1);
        return maxPage;
    }
    public int getPage(String str) {//将输入的页数赋值给Page
        if (str == null) {
            str = "0";
        }
        int Page = Integer.parseInt(str);
        if (Page < 1) {
            Page = 1;
        } else if (((Page - 1) * pagesize + 1) > recordCount) {//当输入的页数大于最大页数时，使它变成最大页数
            Page = maxPage;
        }
        return Page;
    }
    public ArrayList<Items> getInitPage(ArrayList<Items> list, int Page,
            int pagesize) {//初始化分页信息
        ArrayList<Items> newList = new ArrayList<Items>();
        this.list = list;
        this.pagesize = pagesize;
        recordCount = list.size();//查询到的集合的元素个数
        this.maxPage = getMaxPage();//获取最大页数
        for (int i = (Page - 1) * pagesize; i < Page * pagesize; i++) {
            //若是已经遍历完集合了，则终止循环
            if (i >= recordCount) {
                break;
            } else {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    public ArrayList<Items> getAppointPage(int Page) {//获取指定页的数据
        ArrayList<Items> newList = new ArrayList<Items>();
        for (int i = (Page - 1) * pagesize; i < Page * pagesize; i++) {
            //若是已经遍历完集合了，则终止循环
            if (i >= recordCount) {
                break;
            } else {
                newList.add(list.get(i));
            }
        }
        return newList;
    }

    public String printCtrl(int Page, String url, int maxPage) {//输出记录导航的字符串
        String strHtml = "<div><div style=\"width:700px; margin:0 auto;\">";
        if (Page > 1) {
            strHtml = strHtml + "<a class=\"change_page\" href='" + url
                    + "?Page=1" + "'>第一页</a> ";
            strHtml = strHtml + "<a class=\"change_page\" href='" + url
                    + "?Page=" + (Page - 1) + "'>上一页</a> ";
        }
        strHtml = strHtml + "<a class=\"change_nopage\">当前页数:" + Page + "/"
                + maxPage + " &nbsp;</a>";
        if (Page < maxPage) {
            strHtml = strHtml + "<a class=\"change_page\" href='" + url
                    + "?Page=" + (Page + 1) + "'>下一页</a>"
                    + "	<a class=\"change_page\" href='" + url + "?Page="
                    + maxPage + "'>最后一页&nbsp;</a>";
        }
        strHtml = strHtml + "</div></div>";
        return strHtml;
    }
}
