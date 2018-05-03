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
		static Statement stmt=null;//静态
		static PreparedStatement pstmt=null;//动态
		static ResultSet rs=null;
		
		public static void getDriver(){
			try {
				if(Class.forName(driver) != null){
					System.out.println("驱动加载成功！");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("驱动加载失败！");
			}
		}
		public static Connection getConnection(){
			getDriver();
			try {
				conn=DriverManager.getConnection(url,username,password);
				if(conn!=null)
					System.out.println("数据库连接成功！");
			} catch (SQLException e) {
				System.out.println("数据库连接失败！");
				
			}
			return conn;
		}
		

		public static ResultSet Selection(String sql){//静态查询数据库
			conn=getConnection();
			try {
				stmt=conn.createStatement();
				rs=stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}
		public static ResultSet PSelection(String sql,int num,String name[]){//动态查询数据库
			conn=getConnection();
			try {
				pstmt=conn.prepareStatement(sql);
				for(int i=0;i<num;i++)
				pstmt.setString(i+1, name[i]);
				rs=pstmt.executeQuery();
				if(rs!=null){
					System.out.println("查询成功");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}
		public static void Update(String sql){//静态更新数据库
			conn=getConnection();
			try {
				stmt=conn.createStatement();
				int rtn=stmt.executeUpdate(sql);
				if(rtn==0){
					System.out.println("数据库更新失败！");
				}
				else{
					System.out.println("数据库更新成功！");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void PUpdate(String sql,int num,String name[]){//动态更新数据库
			conn=getConnection();
			try {
				pstmt=conn.prepareStatement(sql);
				for(int i=0;i<num;i++){
					pstmt.setString(i+1, name[i]);
				}
				int rtn=pstmt.executeUpdate();
				if(rtn==0){
					System.out.println("数据库更新失败！");
				}
				else{
					System.out.println("数据库更新成功！更新了"+rtn+"个数据！");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public static void main(String[] args) {
			getConnection();
		}
}

