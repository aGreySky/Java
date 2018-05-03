package banker;
import java.util.Scanner;  
  
	public class BankerOfAlgorithm {//BankerOfAlgorithm  银行家算法  
	    int Max[][];  
	    int Allocation[][];  
	    int Need[][];  
	    int Available[];  
	    int Work[];  
	    String name[];  
	    int temp[];  
	    int S = 100, P = 100;  
	    int safequeue[];  
	    int Request[];  
	    Scanner sc;  
  
    public BankerOfAlgorithm() {  
        int text_temp = 100;  
        int Max[][] = new int[text_temp][text_temp];  
        int Allocation[][] = new int[text_temp][text_temp];  
        int Need[][] = new int[text_temp][text_temp];  
        int Available[] = new int[text_temp];  
        int Work[] = new int[text_temp];  
        String name[] = new String[text_temp];  
        int temp[] = new int[text_temp];  
        int S = 100, P = 100;  
        int safequeue[] = new int[text_temp];  
        int Request[] = new int[text_temp];  
  
    }  
  
    public void Showdata() {  
        int i, j, k, l;  
        System.out.println("\t资源分配情况\n");  
        System.out.println("\tMax\t已分配\tNeed");  
        System.out.print("\t");  
        for (j = 0; j < 3; j++) {  
            for (i = 0; i < S; i++) {  
                System.out.print(name[i] + " ");  
            }  
            System.out.print("\t");  
        }  
        System.out.println();  
        for (i = 0; i < P; i++) {  
            System.out.print(i+"\t");  
            for (j = 0; j < S; j++) {  
                System.out.print(Max[i][j] + " ");  
            }  
            System.out.print("\t");  
            for (k = 0; k < S; k++) {  
                System.out.print(Allocation[i][k] + " ");  
            }  
            System.out.print("\t");  
            for (l = 0; l < S; l++) {  
                System.out.print(Need[i][l] + " ");  
            }  
            System.out.println();  
        }  
        System.out.println("\nAvailable");  
        for (i = 0; i < S; i++) {  
            System.out.print(name[i] + " ");  
        }  
        System.out.println();  
        for (i = 0; i < S; i++) {  
            System.out.print(Available[i] + " ");  
        }  
        System.out.println();  
    }  
  
    public int Judgesafe() {  
        int[][] tempwork = new int[100][100];  
        int i, x, k = 0, m, apply;  
        int[] Finish = new int[100];  
        int[] temp = new int[100];  
        int j;  
        int flag = 0;  
        Work = new int[100];  
        for (i = 0; i < S; i++) {  
            Work[i] = Available[i];  
        }  
        for (i = 0; i < P; i++) {  
            apply = 0;  
            for (j = 0; j < S; j++) {  
                if (Finish[i] == 0 && Need[i][j] <= Work[j]) {  
                    apply++;  
                    if (apply == S) {  
                        for (m = 0; m < S; m++) {  
                            tempwork[i][m] = Work[m];  
                            Work[m] = Work[m] + Allocation[i][m];  
                        }  
                        Finish[i] = 1;  
                        temp[k] = i;  
                        i = -1;  
                        k++;  
                        flag++;  
                    }  
                }  
            }  
        }  
        for (i = 0; i < P; i++) {  
            if (Finish[i] == 0) {  
                System.out.println("系统不安全");  
                return -1;  
            }  
        }  
        System.out.println("系统是安全的");  
        System.out.print("分配的序列:");  
        for (i = 0; i < P; i++) {  
            System.out.print(temp[i]);  
            if (i < P - 1) {  
                System.out.print("->");  
            }  
        }  
        System.out.println();  
        return 0;  
    }  
  
    public void Changedata(int flag) {  
        for (int i = 0; i < S; i++) {  
            Available[i] = Available[i] - Request[i];  
            Allocation[flag][i] = Allocation[flag][i] + Request[i];  
            Need[flag][i] = Need[flag][i] - Request[i];  
        }  
    }  
  
    public void Share() {  
        sc = new Scanner(System.in);  
        int i, flag;  
        char ch = 'Y';  
        System.out.println("输入请求资源的进程：");  
        flag = sc.nextInt();  
        Request = new int[100];  
        if (flag >= P) {  
            System.out.println("此进程不存在!");  
        } else {  
            System.out.println("输入此进程对各个资源的请求数量：");  
            for (i = 0; i < S; i++) {  
                Request[i] = sc.nextInt();  
            }  
            for (i = 0; i < S; i++) {  
                if (Request[i] > Need[flag][i]) {  
                    System.out.println("进程" + flag + "申请的资源大于它所需要的资源!");  
                    System.out.println("分配不合理不予分配!");  
                    ch = 'N';  
                    break;  
                } else if (Request[i] > Available[i]) {  
                    System.out.println("进程" + flag + "申请的资源大于可利用的资源。");  
                    System.out.println("分配不合理，不予分配!");  
                    ch = 'N';  
                    break;  
                }  
            }  
            if (ch == 'Y') {  
                Changedata(flag);  
                if (Judgesafe() == -1) {  
                    System.out.println("进程" + flag + "申请资源后，系统进入死锁状态，分配失败!");  
                    for (int j = 0; j < S; j++) {  
                        Available[j] = Available[j] + Request[j];  
                        Allocation[flag][j] = Allocation[flag][j] - Request[j];  
                        Need[flag][j] = Need[flag][j] + Request[j];  
                    }  
                }  
            }  
        }  
    }  
  
    public static void main(String[] args) {  
        BankerOfAlgorithm mytext = new BankerOfAlgorithm();  
        Scanner sc = new Scanner(System.in);  
        int i, j, p, s, number;  
        String choice, tempstring;  
        System.out.println("\t\t操作系统实验六");  
        System.out.println("输入资源种类：");  
        s = sc.nextInt();  
        mytext.S = s;  
        System.out.println("输入资源的名称和数量：");  
        mytext.name = new String[100];  
        mytext.Available = new int[100];  
        for (i = 0; i < s; i++) {  
            tempstring = sc.next();  
            number = sc.nextInt();  
            mytext.name[i] = tempstring;  
            mytext.Available[i] = number;  
        }  
        System.out.println("输入进程的数量：");  
        p = sc.nextInt();  
        mytext.P = p;  
        System.out.println("输入各进程资源最大需求量：");  
        mytext.Max = new int[100][100];  
        for (i = 0; i < p; i++) {  
            for (j = 0; j < s; j++) {  
                mytext.Max[i][j] = sc.nextInt();  
            }  
        }  
        System.out.println("输入各进程资源已分配量：");  
        mytext.Allocation = new int[100][100];  
        for (i = 0; i < p; i++) {  
            for (j = 0; j < s; j++) {  
                mytext.Allocation[i][j] = sc.nextInt();  
                mytext.Available[j] = mytext.Available[j]  
                        - mytext.Allocation[i][j];  
            }  
        }  
        mytext.Need = new int[100][100];  
        for (i = 0; i < p; i++) {  
            for (j = 0; j < s; j++) {  
                mytext.Need[i][j] = mytext.Max[i][j] - mytext.Allocation[i][j];  
            }  
        }  
        mytext.Showdata();  
        mytext.Judgesafe();  
        while (true) {  
            System.out.println("是否分配资源(n是关闭)：");  
            choice = sc.next();  
            char choice_char = choice.charAt(0);  
            switch (choice_char) {  
            case 'n':  
                System.exit(0);  
            default:  
                mytext.Share();  
                break;  
            }  
            mytext.Showdata();  
            mytext.Judgesafe();  
        }  
  
    }  
  
}  

