package action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

import util.DbUtil;

public class RegistAction extends ActionSupport {
    private String username;
    private String password;
    private String message;

    @Override
    public String execute() {
        String sql = "select * from user where username=?";
        String sql1 = "insert into user (username,password) value(?,?)";
        Connection conn = DbUtil.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                this.setMessage("注册失败，该用户名已存在。");
                return ERROR;
            } else {
                ps = conn.prepareStatement(sql1);
                ps.setString(1, username);
                ps.setString(2, password);
                int eu = ps.executeUpdate();
                if (eu > 0) {
                    this.setMessage("注册成功");
                    return SUCCESS;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.setMessage("注册失败，未知错误");
        return ERROR;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
