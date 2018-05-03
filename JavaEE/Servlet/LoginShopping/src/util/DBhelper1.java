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
	static Statement stmt=null;//��̬
	static PreparedStatement pstmt=null;//��̬
	static ResultSet rs=null;
	InitialContext ctx;
	public DBhelper1(){
		try {
			ctx = new InitialContext();
			DataSource ds=(DataSource) ctx.lookup("java:comp/env/LOGINSHOPPING");//��ȡ���ӳض���
			conn=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void Selection(String sql){//��̬��ѯ���ݿ�
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public  ResultSet PSelection(String sql,int num,String name[]){//��̬��ѯ���ݿ�
		
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
	public  void Update(String sql){//��̬�������ݿ�
		
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
	public  void PUpdate(String sql,int num,String name[]){//��̬�������ݿ�
		
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
}
	
