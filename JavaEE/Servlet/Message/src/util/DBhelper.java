package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


	public class DBhelper {
		private   String driver;
		private   String url;
		private   String username;
		private   String password;
		
		public DBhelper(String driver,String url,String username,String password){
			this.driver=driver;
			this.url=url;
			this.username=username;
			this.password=password;
		}
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void setDriver(String driver) {
			this.driver = driver;
		}
		public static Connection conn=null;
		
		
		public  void getDriver(){
			try {
				if(Class.forName(driver) != null){
					System.out.println("�������سɹ���");
				}
			} catch (ClassNotFoundException e) {
				System.out.println("��������ʧ�ܣ�");
			}
		}
		public  Connection getConnection(){
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
}	

		
