package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

	public class DBhelper {
		private static final String driver="com.mysql.jdbc.Driver";
		private static final String url="jdbc:mysql://localhost:3306/message?useUnicode=true&characterencoding=gbk";
		private static final String username="root";
		private static final String password="zzq123456";
		
		public static Connection conn=null;
		static Statement stmt=null;//��̬
		static PreparedStatement pstmt=null;//��̬
		static ResultSet rs=null;
		
		public static void getDriver(){
			try {
				if(Class.forName(driver) != null){
					System.out.println("�������سɹ���");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("��������ʧ�ܣ�");
			}
		}
		public static Connection getConnection(){
			getDriver();
			try {
				conn=DriverManager.getConnection(url,username,password);
				if(conn!=null)
					System.out.println("���ݿ����ӳɹ���");
			} catch (SQLException e) {
				System.out.println("���ݿ�����ʧ�ܣ�");
				
			}
			return conn;
		}
		

		public static ResultSet Selection(String sql){//��̬��ѯ���ݿ�
			conn=getConnection();
			try {
				stmt=conn.createStatement();
				rs=stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}
		public static ResultSet PSelection(String sql,int num,String name[]){//��̬��ѯ���ݿ�
			conn=getConnection();
			try {
				pstmt=conn.prepareStatement(sql);
				for(int i=0;i<num;i++)
				pstmt.setString(i+1, name[i]);
				rs=pstmt.executeQuery();
				if(rs!=null){
					System.out.println("��ѯ�ɹ�");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}
		public static void Update(String sql){//��̬�������ݿ�
			conn=getConnection();
			try {
				stmt=conn.createStatement();
				int rtn=stmt.executeUpdate(sql);
				if(rtn==0){
					System.out.println("���ݿ����ʧ�ܣ�");
				}
				else{
					System.out.println("���ݿ���³ɹ���");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void PUpdate(String sql,int num,String name[]){//��̬�������ݿ�
			conn=getConnection();
			try {
				pstmt=conn.prepareStatement(sql);
				for(int i=0;i<num;i++){
					pstmt.setString(i+1, name[i]);
				}
				int rtn=pstmt.executeUpdate();
				if(rtn==0){
					System.out.println("���ݿ����ʧ�ܣ�");
				}
				else{
					System.out.println("���ݿ���³ɹ���������"+rtn+"�����ݣ�");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void main(String[] args) {
			getConnection();
		}
}

