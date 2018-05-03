package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import entity.Student;
import util.DBhelper;

public class StudentDao {
	public boolean login(Student stu) throws Exception{
		DBhelper db=new DBhelper();
		Connection conn=db.initConnection();
		String sql="select stuname,stupwd from T_STUDENT where stuname=? and stupwd=?";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, stu.getStuname());
		ps.setString(2, stu.getStupwd());
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
}
