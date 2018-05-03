package action;

import com.opensymphony.xwork2.ActionSupport;

import vo.Book;

public class SaveBookAction extends ActionSupport {

    private Book book;

    @Override
    public String execute() {
        return SUCCESS;
    }

    //重写校验方法
    @Override
    public void validate() {
        if (book.getNumber().equals("") || book.getNumber() == null) {
            addFieldError("book.number", "书号不能为空");
        } else if (!checkBookNumber(book.getNumber())) {
            addFieldError("book.number", "请输入正确的书号");
        } else if (book.getPublisher().equals("")
                || book.getPublisher() == null) {
            addFieldError("book.publisher", "出版社不能为空");
        } else if (!book.getPublisher().contains("出版社")) {
            addFieldError("book.publisher", "请输入正确的出版社");
        }
    }
    public boolean checkBookNumber(String bookNumber) {
        int sum = 0; // 求和
        int M, N;
        String bookNoPre;
        int checkNo; // 尾号
        String sb = ""; // 12位字符串
        String[] str = bookNumber.split("-");
        int[] nums = new int[str.length]; // nums：以“-”隔开的整型数组
        for (int i = 0; i < str.length; i++) {
            if (str[i] != null) {
                try {
                    nums[i] = Integer.parseInt(str[i]);
                } catch (NumberFormatException ex) {
                    addFieldError("book.number", "书号不能包含字母或符号(除分割符“-”)");
                    return false;
                }
            }
        }

        // 将字符串数组转换成字符串
        for (int i = 0; i < str.length; i++) {
            sb += str[i];
        }

        // 将获取的字符串存到bookNoPre中
        bookNoPre = bookNumber.substring(0, bookNumber.length() - 1);
        // System.out.println("字符串为："+sb);

        // 将字符串转换成12位的整型数组并求和
        int[] num = new int[sb.length()]; // num表示把字符串变成12位数组
        int lastNum = Integer
                .parseInt(sb.substring(sb.length() - 1, sb.length()));
        System.out.println("最后一个数为:" + lastNum);
        for (int i = 0; i < sb.length() - 1; i++) {
            num[i] = Integer.parseInt(sb.substring(i, i + 1));
            // System.out.println("num["+i+"]="+num[i]);
            if (i % 2 == 0) {
                sum += num[i];
            } else {
                sum += num[i] * 3;
            }
        }
        System.out.println("sum=" + sum);
        M = sum % 10;
        N = 10 - M;
        if (N == 10) {
            checkNo = 0;
        } else {
            checkNo = N;
        }
        System.out.println("尾号=" + bookNoPre + checkNo);
        return lastNum == checkNo;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
