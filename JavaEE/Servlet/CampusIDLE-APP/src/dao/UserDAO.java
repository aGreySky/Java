package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import util.DBhelper;

public class UserDAO {
    private String serverImagesPath = "http://xiaoyou.free.ngrok.cc/CampusIDLE-WEB/images/usersPicture/";
    DBhelper db;
    public UserDAO(DBhelper db) {
        this.db = db;
    }

    // 查询用户是否存在
    public boolean ifExist(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        String sql = "select * from user where username=? or phone=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPhone());
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
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
        return flag;
    }

    // 通过手机号查询用户是否存在
    public boolean ifPhoneExist(String phone) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        final String sql = "select * from user where phone=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
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
        return flag;
    }

    // 添加用户
    public void addUser(final User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        final ResultSet rs = null;
        final String sql = "insert into user (username,password,useremail,phone,rank,state,code) value(?,?,?,?,?,?,?)";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
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
        } catch (final Exception e) {
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
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 通过手机号密码查询用户身份
    public User ifRightByPhone(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from user where phone=? and password=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getPhone());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setUseremail(rs.getString("useremail"));
                user.setRank(rs.getInt("rank"));
                user.setId(rs.getInt("id"));
                user.setPicture(serverImagesPath + rs.getString("picture"));
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
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUseremail());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setUseremail(rs.getString("useremail"));
                user.setRank(rs.getInt("rank"));
                user.setId(rs.getInt("id"));
                user.setPicture(serverImagesPath + rs.getString("picture"));
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
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setUseremail(rs.getString("useremail"));
                user.setRank(rs.getInt("rank"));
                user.setId(rs.getInt("id"));
                user.setPicture(serverImagesPath + rs.getString("picture"));
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

    // 通过id查找用户
    public User getUserByUserId(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        final User user = new User();
        final String sql = "select * from user where id=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setId(rs.getInt("id"));
                if (rs.getString("picture") != null) {
                    user.setPicture(serverImagesPath + rs.getString("picture"));
                }
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
        return user;
    }

    // 根据手机号修改用户密码
    public boolean updatePwdByPhone(String phone, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            String sql = "update user set password =? where phone=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, phone);
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

    // 修改用户信息
    public boolean updateUser(int id, String sql, String[] update) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
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

    //修改用户头像
    public boolean updateUserPicture(int id, String picture) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            String sql = "update user set picture =? where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, picture);
            ps.setInt(2, id);
            if (ps.executeUpdate() > 0) {
                return true;
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
            }
        }
        return false;
    }

    //添加反馈意见
    public boolean addAdvice(int uid, String advice) {
        Connection conn = null;
        PreparedStatement ps = null;
        final ResultSet rs = null;
        final String sql = "insert into advice (userId,advice) value(?,?)";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
            ps.setString(2, advice);
            if (ps.executeUpdate() > 0) {
                return true;
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
        return false;
    }
}
