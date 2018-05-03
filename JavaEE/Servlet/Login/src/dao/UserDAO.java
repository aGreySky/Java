package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import entity.User;
import util.DBHelper;

public class UserDAO {

//  �ж��û��Ƿ��Ѿ�����
    public boolean isExistByName(String username){
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet userSet = null;

        try{
            //��1. �������ݿ⡿
            conn = DBHelper.getConnection();
            //�� 2. ִ��SQL��䣬����ִ�н����ResultSet ��           
            String sql = "select * from user where name=?";
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, username);
            userSet = preStmt.executeQuery();
            //�� 3. ����ִ�н����ResultSet��
            if(userSet.next()){
            	 System.out.println("�û���"+userSet.getString(1));
                return true;
            }
            return false;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }finally{
            //�� 4. ��Ҫ�Ĺر�ResultSet��Statement ��
            if(userSet != null){
                try {
                    userSet.close();
                    userSet = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if(preStmt != null){
                try {
                    preStmt.close();
                    preStmt = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
//  �����û�����
    public String addUser(User user){
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet affectedSet = null;

        String id = null;

        try{
        	conn = DBHelper.getConnection();
            String sql = "insert into user(name,password) values(?,?)";
//          [!]ע�������insert��ȡid��д��
            preStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preStmt.setString(1, user.getName());
            preStmt.setString(2, user.getPassword());
            preStmt.executeUpdate();
            affectedSet = preStmt.getGeneratedKeys();
            if(affectedSet.next()){
                id = affectedSet.getString(1);
            }
            return id;
        }catch(MySQLIntegrityConstraintViolationException ex){
            ex.printStackTrace();
            return null;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }finally{
            if(affectedSet != null){
                try {
                    affectedSet.close();
                    affectedSet = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if(preStmt != null){
                try {
                    preStmt.close();
                    preStmt = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
		
    }
//  ��½
//  �ж��û������û������Ƿ�ƥ��
    public String userLogin(User user){
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet userSet = null;

        try{
            conn = DBHelper.getConnection();
            String sql = "select id from user where name=? and password=?";
            preStmt = conn.prepareStatement(sql);
            preStmt.setString(1, user.getName());
            preStmt.setString(2, user.getPassword());
            userSet = preStmt.executeQuery();
            if(userSet.next()){
                return userSet.getString("id");
            }
            return "false";
        }catch(Exception ex){
            ex.printStackTrace();
            return "false";
        }finally{
            //�� 4. ��Ҫ�Ĺر�ResultSet��Statement ��
            if(userSet != null){
                try {
                    userSet.close();
                    userSet = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if(preStmt != null){
                try {
                    preStmt.close();
                    preStmt = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]){
        UserDAO userdao = new UserDAO();
        User user = new User();
        user.setName("123qwe");
        user.setPassword("123456");
        System.out.println(userdao.addUser(user));
        System.out.println(userdao.userLogin(user));
        System.out.println("���");
    }
}