package dao;
import util.DBhelper1;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
	
public class UserDAO {
		User user=new User();
		DBhelper1 db=new DBhelper1();
		public boolean ifExist(String name) throws SQLException{   //查询用户是否存在
			String sql="select * from user where name=?";
			String[] username=new String[1];
			username[0]=name;
			ResultSet rs=null;
			rs=db.PSelection(sql,1,username);
			return rs.next();
		}
		public void addUser(String name,String password){      //添加用户
			String sql="insert into user (name,password) value(?,?)";
			String[] username={name,password};
			db.PUpdate(sql, 2, username);
		}
		public boolean ifRight(String name,String password) throws SQLException{   //查询用户身份
			String sql="select * from user where name=?";
			String[] username=new String[1];
			username[0]=name;
			ResultSet rs=null;
			rs=db.PSelection(sql,1,username);
			if(rs.next()&&(rs.getString("password").equals(password))){
				return true;
			}
			return false;
		}
}
