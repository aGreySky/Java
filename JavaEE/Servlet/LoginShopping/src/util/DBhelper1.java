package util;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBhelper1 {
	public static Connection conn=null;
	static Statement stmt=null;//静态
	static PreparedStatement pstmt=null;//动态
	static ResultSet rs=null;
	InitialContext ctx;
	public DBhelper1(){
		try {
			ctx = new InitialContext();
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/LOGINSHOPPING");//获取连接池对象
			conn=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void Selection(String sql){//静态查询数据库
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public  ResultSet PSelection(String sql,int num,String name[]){//动态查询数据库
		
		try {
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<num;i++)
			pstmt.setString(i+1, name[i]);
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public  void Update(String sql){//静态更新数据库
		
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
	public  void PUpdate(String sql,int num,String name[]){//动态更新数据库
		
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
}
	
