package bankerSystem;
import java.util.Scanner;
public class test {
	static Scanner sc=new Scanner(System.in);
	static int s1=0;//计数变量--货币种类
	static int s2=0;//计数变量--顾客数量
	static String[] moneyName;//货币名称
	static int[] Available;//银行家信息
	static String[] clientName;//顾客信息
	static int[][] Need;
	static int[][] Allocation;
	static int[][] Max;
	public static void main(String[] args) {
		System.out.println("\t\t银行家系统");  
		System.out.println("输入货币的种类：");  
        s1=sc.nextInt();
        moneyName=new String[s1];
        Available=new int[s1];
        if(s1<=0){
        	System.out.println("输入有误！");
        	return;
        }else{
        	System.out.println("输入各种货币的名称和"
        	+ "在银行中存放的数量(单位：十亿)："); 
        	for(int i=0;i<s1;i++){
        		moneyName[i]=sc.next();
        		Available[i]=sc.nextInt();
        	}
        }
        System.out.println("输入顾客的个数：");  
        s2 = sc.nextInt();  
        clientName=new String[s2];
        Allocation=new int[s2][s1];
        Max=new int[s2][s1];
        Need=new int[s2][s1];
        if(s2<=0){
        	System.out.println("输入有误！");
        	return;
        }else{
        	System.out.println("输入顾客的信息"); 
        	for(int i=0;i<s2;i++){
        		init(i);
        	}
        }
     Showdata();
     Judge();
	}
	public static void init(int num){//初始化用户信息
		System.out.println("-------------请输入第("+(num+1)+"/"+s2+")个顾客的信息-----------");
		System.out.println("姓名：");
		clientName[num]=sc.next();
		for(int i=0;i<s1;i++){
			System.out.println("\t"+moneyName[i]+"的最大需求量：");
			Max[num][i]=sc.nextInt();
			System.out.println("\t"+moneyName[i]+"已分配到的量：");
			Allocation[num][i]=sc.nextInt();
			Available[i]=Available[i]-Allocation[num][i];//银行的可用资金计算--需要减去已分配给顾客的资金
			Need[num][i]=Max[num][i]-Allocation[num][i];//顾客所需要的资金等于最大贷款量减去已分配的量
		}
	}
	
	public static void Showdata() { //显示信息 
        int i, j, k, l;  
        System.out.println("----------------资源分配情况-------------------\n");  
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
		int work[]=new int[s1];//可用的资源变量
		int flag[]=new int[s2];//判断是否借过款
		for(int i=0;i<s1;i++)
			work[i]=Available[i];//初始化为银行剩余的可用资源量
		int right;//判断是否能运行
		int temp[]=new int[s2];
		int l=0;//temp的参数
		for(int i=0;i<s2;i++){//顾客
			right=0;
			for(int j=0;j<s1;j++){//货币
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
				System.out.println("银行资金不够，不能借款！");
				return -1;
			}
		}
		System.out.println("恭喜！银行可以借款");
		System.out.println("借款的顺序为：");
		for (int i=0; i < s2; i++) {  
            System.out.print(clientName[temp[i]]);  
            if (i < s2 - 1) System.out.print("->");    
        }  
        System.out.println();  
        return 0;  
	}
}
