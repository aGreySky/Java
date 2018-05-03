package dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import entity.GuestBook;
	 
public class messageDAO {
	private Connection conn;
	 private Statement  stm;
	 private PreparedStatement  ps;
	 private ResultSet  rs;
	 private ArrayList<GuestBook> list=new ArrayList<GuestBook>();
	 public messageDAO(Connection conn)  {
			this.conn=conn;	
		}
	public int update(String sql,GuestBook gb){      //‘ˆ…æ∏ƒ¡Ù—‘
			int i=0;
			 try {
				ps=(PreparedStatement) conn.prepareStatement(sql);
				ps.setString(1, gb.getName());
				ps.setString(2,gb.getTitle());
				ps.setString(3,gb.getContent());
				 i=ps.executeUpdate();
			} catch (SQLException e) {
					e.printStackTrace();
			}
			return i;	 
		
	}
	public ArrayList<GuestBook> selectAll(String sql){   //≤È—Ø¡Ù—‘
			 try {
				stm=(Statement) conn.createStatement();
				 rs=stm.executeQuery(sql);
				 while(rs.next()){
					 GuestBook gb=new GuestBook();
					 gb.setName(rs.getString("name"));
					 gb.setTitle(rs.getString("title"));
					 gb.setContent(rs.getString("content"));
					 list.add(gb);
					 
				 }
				 System.out.println("size="+list.size());
			} catch (SQLException e) {
		}
		return list;
	}
	
}
