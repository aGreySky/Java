package banker;
import java.util.Scanner;  
  
	public class BankerOfAlgorithm {//BankerOfAlgorithm  ���м��㷨  
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
        System.out.println("\t��Դ�������\n");  
        System.out.println("\tMax\t�ѷ���\tNeed");  
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
                System.out.println("ϵͳ����ȫ");  
                return -1;  
            }  
        }  
        System.out.println("ϵͳ�ǰ�ȫ��");  
        System.out.print("���������:");  
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
        System.out.println("����������Դ�Ľ��̣�");  
        flag = sc.nextInt();  
        Request = new int[100];  
        if (flag >= P) {  
            System.out.println("�˽��̲�����!");  
        } else {  
            System.out.println("����˽��̶Ը�����Դ������������");  
            for (i = 0; i < S; i++) {  
                Request[i] = sc.nextInt();  
            }  
            for (i = 0; i < S; i++) {  
                if (Request[i] > Need[flag][i]) {  
                    System.out.println("����" + flag + "�������Դ����������Ҫ����Դ!");  
                    System.out.println("���䲻���������!");  
                    ch = 'N';  
                    break;  
                } else if (Request[i] > Available[i]) {  
                    System.out.println("����" + flag + "�������Դ���ڿ����õ���Դ��");  
                    System.out.println("���䲻�����������!");  
                    ch = 'N';  
                    break;  
                }  
            }  
            if (ch == 'Y') {  
                Changedata(flag);  
                if (Judgesafe() == -1) {  
                    System.out.println("����" + flag + "������Դ��ϵͳ��������״̬������ʧ��!");  
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
        System.out.println("\t\t����ϵͳʵ����");  
        System.out.println("������Դ���ࣺ");  
        s = sc.nextInt();  
        mytext.S = s;  
        System.out.println("������Դ�����ƺ�������");  
        mytext.name = new String[100];  
        mytext.Available = new int[100];  
        for (i = 0; i < s; i++) {  
            tempstring = sc.next();  
            number = sc.nextInt();  
            mytext.name[i] = tempstring;  
            mytext.Available[i] = number;  
        }  
        System.out.println("������̵�������");  
        p = sc.nextInt();  
        mytext.P = p;  
        System.out.println("�����������Դ�����������");  
        mytext.Max = new int[100][100];  
        for (i = 0; i < p; i++) {  
            for (j = 0; j < s; j++) {  
                mytext.Max[i][j] = sc.nextInt();  
            }  
        }  
        System.out.println("�����������Դ�ѷ�������");  
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
            System.out.println("�Ƿ������Դ(n�ǹر�)��");  
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

