package dao;
import util.DBhelper1;

import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
	
public class UserDAO {
		User user=new User();
		DBhelper1 db=new DBhelper1();
		public boolean ifExist(String name) throws SQLException{   //��ѯ�û��Ƿ����
			String sql="select * from user where username=?";
			String[] username=new String[1];
			username[0]=name;
			ResultSet rs=null;
			rs=db.PSelection(sql,1,username);
			return rs.next();
		}
		public void addUser(User user){      //����û�
			String sql="insert into user (username,password,useremail,phone) value(?,?,?,?)";
			String[] username={user.getUsername(),user.getPassword(),user.getUseremail(),user.getPhone()};
			db.PUpdate(sql, 4, username);
		}
		public boolean ifRight(User user) throws SQLException{   //��ѯ�û����
			String sql="select * from user where username=?";
			String[] username=new String[1];
			username[0]=user.getUsername();
			ResultSet rs=null;
			rs=db.PSelection(sql,1,username);
			if(rs.next()&&(rs.getString("password").equals(user.getPassword()))){
				return true;
			}
			return false;
		}
}
