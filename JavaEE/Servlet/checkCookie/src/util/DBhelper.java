package util;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;


	public class DBhelper {
		private String driver="com.mysql.jdbc.Driver";
		private String url="jdbc:mysql://localhost:3306/checkCookie";
		private String username="root";
		private String password="zzq123456";
		public static Connection conn=null;

		public  void getDriver(){
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				System.out.println("��������ʧ�ܣ�");
			}
		}
		public  Connection initConnection(){
			getDriver();
			try {
				conn=(Connection) DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
				System.out.println("���ݿ�����ʧ�ܣ�");
				
			}
			return conn;
		}
	}		
		
	

		
