package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import entity.User;
import util.DBhelper;
import util.MailUtil;

public class UserDAO {
    DBhelper db;
    public UserDAO(final DBhelper db) {
        this.db = db;
    }

    // 查询用户是否存在
    public boolean ifExist(final User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        final String sql = "select * from user where username=?";
        try {
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    // 查询手机号和邮箱账号是否被注册
    public boolean ifPhoneOrEmailExist(final User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        final String sql = "select * from user where phone =? or useremail=?";
        try {
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, user.getPhone());
            ps.setString(2, user.getUseremail());
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    // 添加用户
    public boolean addUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into user (username,password,useremail,phone,rank,state,code) value(?,?,?,?,?,?,?)";
        try {
            if (!MailUtil.sendMail(user.getUseremail(), user.getCode())) {
                return false;
            }
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUseremail());
            ps.setString(4, user.getPhone());
            ps.setInt(5, 1);
            ps.setString(6, user.getState());
            ps.setString(7, user.getCode());
            if (ps.executeUpdate() > 0) {
                System.out.println("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    // 通过手机号密码查询用户身份
    public User ifRightByPhone(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from user where phone=? and password=?";
        try {
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, user.getPhone());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setUseremail(rs.getString("useremail"));
                user.setRank(rs.getInt("rank"));
                user.setId(rs.getInt("id"));
                user.setPicture(rs.getString("picture"));
                user.setState(rs.getString("state"));
                user.setCode(rs.getString("code"));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    // 通过邮箱密码查询用户身份
    public User ifRightByUseremail(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from user where useremail=? and password=?";
        try {
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, user.getUseremail());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setUseremail(rs.getString("useremail"));
                user.setRank(rs.getInt("rank"));
                user.setId(rs.getInt("id"));
                user.setPicture(rs.getString("picture"));
                user.setState(rs.getString("state"));
                user.setCode(rs.getString("code"));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    // 通过用户名密码查询用户身份
    public User ifRightByUsername(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from user where username=? and password=?";
        try {
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setUseremail(rs.getString("useremail"));
                user.setRank(rs.getInt("rank"));
                user.setId(rs.getInt("id"));
                user.setPicture(rs.getString("picture"));
                user.setState(rs.getString("state"));
                user.setCode(rs.getString("code"));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    // 通过用户名邮箱查询用户身份
    public int ifRightByEmail(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        final String sql = "select * from user where username=?";
        try {
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            rs = ps.executeQuery();
            if (rs.next() && (rs.getString("useremail")
                    .equals(user.getUseremail()))) {
                return rs.getInt("id");
            }
        } catch (final SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    // 通过用户名查找用户
    public User getUserByUserId(final int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();
        String sql = "select * from user where id=?";
        try {
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setUseremail(rs.getString("useremail"));
                user.setRank(rs.getInt("rank"));
                user.setId(rs.getInt("id"));
                user.setPicture(rs.getString("picture"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (final SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    // 修改用户信息
    public boolean updateUser(int id, String sql, String[] update) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            int i = 0;
            for (; i < update.length; i++) {
                ps.setString(i + 1, update[i]);
            }
            ps.setInt(i + 1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    // 根据激活码查询用户
    public User findUserByCode(String code) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();
        String sql = "select * from user where code=?";
        try {
            conn = db.getConnection();
            ps = (PreparedStatement) conn.prepareStatement(sql);
            ps.setString(1, code);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setUseremail(rs.getString("useremail"));
                user.setRank(rs.getInt("rank"));
                user.setId(rs.getInt("id"));
                user.setPicture(rs.getString("picture"));
                user.setCode(rs.getString("code"));
                user.setState(rs.getString("state"));
                System.out.println("Yes");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
