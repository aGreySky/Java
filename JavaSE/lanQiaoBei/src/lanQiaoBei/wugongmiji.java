package lanQiaoBei;

//标题：武功秘籍
//
//小明到X山洞探险，捡到一本有破损的武功秘籍（2000多页！当然是伪造的）。他注意到：书的第10页和第11页在同一张纸上，但第11页和第12页不在同一张纸上。
//
//小明只想练习该书的第81页到第92页的武功，又不想带着整本书。请问他至少要撕下多少张纸带走？
//
//这是个整数，请通过浏览器提交该数字，不要填写任何多余的内容。

// 1 23 45 67 89 
public class wugongmiji {

    public static void main(String[] args) {
        int startPage = 81;
        int endPage = 92;
        int sum = 0;
        if (startPage % 2 != 0 && endPage % 2 == 0) {
            sum = (endPage - startPage) / 2 + 2;
        } else {
            sum = (endPage - startPage) / 2 + 1;
        }
        System.out.println(sum);
    }

}
