package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import entity.Items;
import util.DBhelper2;


public class ItemDAO {
	DBhelper2 db=new DBhelper2();
	Connection conn=db.conn;
	PreparedStatement stmt =null;
	ResultSet rs=null;
	public ArrayList<Items> getAllItems(){//添加所有商品进集合
		ArrayList<Items> list =new ArrayList<Items>();//商品集合
		try
		{
			String sql="select *from items;";//sql语句
			stmt =conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				Items item =new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setPrice(rs.getInt("price"));
				item.setNumber(rs.getInt("number"));
				item.setPicture(rs.getString("picture"));
				list.add(item);//把一个商品加入集合
			}
			return list;//返回集合
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally
		{
			//释放数据集对象
			if(rs!=null)
			{
				try
				{
					rs.close();
					rs=null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			//释放语句对象
			if(stmt!=null)
			{
				try
				{
					stmt.close();
					stmt=null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	}
	public Items getItemById(int id){//根据商品编号获取商品信息
		
		try
		{
			String sql="select *from items where id=?;";//sql语句
			stmt =conn.prepareStatement(sql);
			stmt.setInt(1,id);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				Items item =new Items();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setCity(rs.getString("city"));
				item.setPrice(rs.getInt("price"));
				item.setNumber(rs.getInt("number"));
				item.setPicture(rs.getString("picture"));
				return item;
			}
			else
			{
			return null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		finally
		{
			//释放数据集对象
			if(rs!=null)
			{
				try
				{
					rs.close();
					rs=null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			//释放语句对象
			if(stmt!=null)
			{
				try
				{
					stmt.close();
					stmt=null;
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
			
		}	
	}
}
