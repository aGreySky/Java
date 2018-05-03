package lanQiaoBei;
//有一堆煤球，堆成三角棱锥形。具体：
//第一层放1个，
//第二层3个（排列成三角形），
//第三层6个（排列成三角形），
//第四层10个（排列成三角形），
//....
//如果一共有100层，共有多少个煤球？
public class meiqiu {
    public static void main(String[] args) {
        int sum = 0;
        int front = 0;
        for (int n = 1; n <= 100; n++) {
            front += n;
            sum += front;
        }
        System.out.println("煤球有：" + sum + "个");
    }
}
