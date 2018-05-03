package action;

import com.opensymphony.xwork2.ActionSupport;

import vo.Book;

public class SaveBookAction extends ActionSupport {

    private Book book;

    @Override
    public String execute() {
        return SUCCESS;
    }

    //��дУ�鷽��
    @Override
    public void validate() {
        if (book.getNumber().equals("") || book.getNumber() == null) {
            addFieldError("book.number", "��Ų���Ϊ��");
        } else if (!checkBookNumber(book.getNumber())) {
            addFieldError("book.number", "��������ȷ�����");
        } else if (book.getPublisher().equals("")
                || book.getPublisher() == null) {
            addFieldError("book.publisher", "�����粻��Ϊ��");
        } else if (!book.getPublisher().contains("������")) {
            addFieldError("book.publisher", "��������ȷ�ĳ�����");
        }
    }
    public boolean checkBookNumber(String bookNumber) {
        int sum = 0; // ���
        int M, N;
        String bookNoPre;
        int checkNo; // β��
        String sb = ""; // 12λ�ַ���
        String[] str = bookNumber.split("-");
        int[] nums = new int[str.length]; // nums���ԡ�-����������������
        for (int i = 0; i < str.length; i++) {
            if (str[i] != null) {
                try {
                    nums[i] = Integer.parseInt(str[i]);
                } catch (NumberFormatException ex) {
                    addFieldError("book.number", "��Ų��ܰ�����ĸ�����(���ָ����-��)");
                    return false;
                }
            }
        }

        // ���ַ�������ת�����ַ���
        for (int i = 0; i < str.length; i++) {
            sb += str[i];
        }

        // ����ȡ���ַ����浽bookNoPre��
        bookNoPre = bookNumber.substring(0, bookNumber.length() - 1);
        // System.out.println("�ַ���Ϊ��"+sb);

        // ���ַ���ת����12λ���������鲢���
        int[] num = new int[sb.length()]; // num��ʾ���ַ������12λ����
        int lastNum = Integer
                .parseInt(sb.substring(sb.length() - 1, sb.length()));
        System.out.println("���һ����Ϊ:" + lastNum);
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
        System.out.println("β��=" + bookNoPre + checkNo);
        return lastNum == checkNo;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
