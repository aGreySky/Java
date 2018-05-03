package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	private static final String driver="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/login?useUnicode=true&characterencoding=gbk";
	private static final String username="root";
	private static final String password="zzq123456";
	
	public static Connection conn=null;

	static 
	{
		try
		{
			Class.forName(driver);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception
	{
		if(conn==null)
		{
			conn=DriverManager.getConnection(url,username,password);
			return conn;
		}
		return conn;
	}

	public static void main(String[] args) {
			try{
				Connection conn=DBHelper.getConnection();
				if(conn!=null)
				{
					System.out.println("数据库连接成功！");
				}
				else
				{
					System.out.println("数据库连接失败！");
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
	}
}
