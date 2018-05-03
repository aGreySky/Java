package dao;

import util.DBhelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class messageDAO {
	public void addMessage(String name,String title,String content){      //ÃÌº”¡Ù—‘
		String sql="insert into messageList (name,title,content) value(?,?,?)";
		String[] username={name,title,content};
		DBhelper.PUpdate(sql, 3, username);
	}
	public String[] turn() throws SQLException{   //≤È—Ø¡Ù—‘
		String sql="select * from messageList ORDER BY id desc";
		ResultSet rs=null;
		rs=DBhelper.Selection(sql);
		String[] content=new String[3];
		if(rs.next()){
			content[0]=rs.getString("name");
			content[1]=rs.getString("title");
			content[2]=rs.getString("content");
		}
		return content;
		
		
	}
	
}
