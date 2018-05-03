package bankerSystem;
import java.util.Scanner;
public class test {
	static Scanner sc=new Scanner(System.in);
	static int s1=0;//��������--��������
	static int s2=0;//��������--�˿�����
	static String[] moneyName;//��������
	static int[] Available;//���м���Ϣ
	static String[] clientName;//�˿���Ϣ
	static int[][] Need;
	static int[][] Allocation;
	static int[][] Max;
	public static void main(String[] args) {
		System.out.println("\t\t���м�ϵͳ");  
		System.out.println("������ҵ����ࣺ");  
        s1=sc.nextInt();
        moneyName=new String[s1];
        Available=new int[s1];
        if(s1<=0){
        	System.out.println("��������");
        	return;
        }else{
        	System.out.println("������ֻ��ҵ����ƺ�"
        	+ "�������д�ŵ�����(��λ��ʮ��)��"); 
        	for(int i=0;i<s1;i++){
        		moneyName[i]=sc.next();
        		Available[i]=sc.nextInt();
        	}
        }
        System.out.println("����˿͵ĸ�����");  
        s2 = sc.nextInt();  
        clientName=new String[s2];
        Allocation=new int[s2][s1];
        Max=new int[s2][s1];
        Need=new int[s2][s1];
        if(s2<=0){
        	System.out.println("��������");
        	return;
        }else{
        	System.out.println("����˿͵���Ϣ"); 
        	for(int i=0;i<s2;i++){
        		init(i);
        	}
        }
     Showdata();
     Judge();
	}
	public static void init(int num){//��ʼ���û���Ϣ
		System.out.println("-------------�������("+(num+1)+"/"+s2+")���˿͵���Ϣ-----------");
		System.out.println("������");
		clientName[num]=sc.next();
		for(int i=0;i<s1;i++){
			System.out.println("\t"+moneyName[i]+"�������������");
			Max[num][i]=sc.nextInt();
			System.out.println("\t"+moneyName[i]+"�ѷ��䵽������");
			Allocation[num][i]=sc.nextInt();
			Available[i]=Available[i]-Allocation[num][i];//���еĿ����ʽ����--��Ҫ��ȥ�ѷ�����˿͵��ʽ�
			Need[num][i]=Max[num][i]-Allocation[num][i];//�˿�����Ҫ���ʽ��������������ȥ�ѷ������
		}
	}
	
	public static void Showdata() { //��ʾ��Ϣ 
        int i, j, k, l;  
        System.out.println("----------------��Դ�������-------------------\n");  
        System.out.print("Name\t");  
        for(int a=0;a<s1;a++) System.out.print("  ");
        System.out.print("\tMax\t");
        for(int a=0;a<s1;a++) System.out.print("  ");
        System.out.print("\tAllocation\t");  
        for(int a=0;a<s1;a++) System.out.print(" ");
        System.out.println("Need\t");
        for (j = 0; j < 3; j++) {  
        	System.out.print("\t\t"); 
            for (i = 0; i < s1; i++) System.out.print(moneyName[i] + "  ");   
        }
        System.out.println();  
        for (i = 0; i < s2; i++) {  
            System.out.print(clientName[i]+"\t\t");  
            for (j = 0; j < s1; j++) System.out.print(Max[i][j] + "  ");    
            System.out.print("\t\t");  
            for (k = 0; k < s1; k++) System.out.print(Allocation[i][k] + "  ");
            System.out.print("\t\t");  
            for (l = 0; l < s1; l++) System.out.print(Need[i][l] + "  "); 
            System.out.println();  
        }  
        System.out.println("\nAvailable");  
         for (i = 0; i < s1; i++) System.out.print(moneyName[i] + " "); 
        System.out.println();  
         for (i = 0; i < s1; i++) System.out.print(Available[i] + "  ");  
        System.out.println();  
    }  
	
	public static int Judge(){
		int work[]=new int[s1];//���õ���Դ����
		int flag[]=new int[s2];//�ж��Ƿ�����
		for(int i=0;i<s1;i++)
			work[i]=Available[i];//��ʼ��Ϊ����ʣ��Ŀ�����Դ��
		int right;//�ж��Ƿ�������
		int temp[]=new int[s2];
		int l=0;//temp�Ĳ���
		for(int i=0;i<s2;i++){//�˿�
			right=0;
			for(int j=0;j<s1;j++){//����
				if(flag[i]==0&&Need[i][j]<=work[j]){
					right++;
					if(right==s1){
						for(int k=0;k<s1;k++) 
							work[k]=work[k]+Allocation[i][k];
						temp[l]=i;
						l++;
						flag[i]=1;
						i=0;
						break;
					}
				}
			}		
		}
		for(int i=0;i<s2;i++){
			if(flag[i]==0){
				System.out.println("�����ʽ𲻹������ܽ�");
				return -1;
			}
		}
		System.out.println("��ϲ�����п��Խ��");
		System.out.println("����˳��Ϊ��");
		for (int i=0; i < s2; i++) {  
            System.out.print(clientName[temp[i]]);  
            if (i < s2 - 1) System.out.print("->");    
        }  
        System.out.println();  
        return 0;  
	}
}
