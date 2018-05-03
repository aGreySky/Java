package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Friends;
import entity.Items;
import entity.User;
import util.DBhelper;

public class FriendsDAO {
    private String serverImagesPath = "http://xiaoyou.free.ngrok.cc/CampusIDLE-WEB/images/friendsPicture/";
    DBhelper db;
    public FriendsDAO(DBhelper db) {
        this.db = db;
    }

    /*
     * �����н��ѽ��еĲ��� ���е�¼���û����ܽ���
     */

    // ������н�����Ϣ
    public ArrayList<Friends> getAllFriends() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Friends> list = new ArrayList<Friends>();// ���Ѽ���
        try {
            conn = db.getConnection();
            String sql = "select *from friends;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Friends friends = new Friends();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                friends.setId(rs.getInt("id"));
                friends.setOverview(rs.getString("overview"));
                friends.setWay(rs.getString("way"));
                friends.setContact(rs.getString("contact"));
                friends.setPicture(serverImagesPath + rs.getString("picture"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                friends.setUser(user);
                list.add(friends);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ���ݽ��ѱ�Ż�ý�������
    public Friends getFriendsById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Friends friends = new Friends();
        try {
            conn = db.getConnection();
            String sql = "select *from friends where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                friends.setId(rs.getInt("id"));
                friends.setOverview(rs.getString("overview"));
                friends.setWay(rs.getString("way"));
                friends.setContact(rs.getString("contact"));
                friends.setPicture(serverImagesPath + rs.getString("picture"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                friends.setUser(user);
            } else {
                friends = null;
            }
            return friends;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // ���ݽ��������ý�������
    public ArrayList<Friends> getFriendsByType() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Friends> list = new ArrayList<Friends>();

        try {
            conn = db.getConnection();
            String sql = "select *from friends;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Friends friends = new Friends();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                friends.setId(rs.getInt("id"));
                friends.setPicture(serverImagesPath + rs.getString("picture"));
                friends.setOverview(rs.getString("overview"));
                friends.setWay(rs.getString("way"));
                friends.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                friends.setUser(user);
                list.add(friends);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // ��������
    public ArrayList<Items> getFriendsByUserCdn(String sql) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();// ���Ѽ���

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Items friends = new Items();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                friends.setId(rs.getInt("id"));
                friends.setPicture(serverImagesPath + rs.getString("picture"));
                friends.setOverview(rs.getString("overview"));
                friends.setWay(rs.getString("way"));
                friends.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                friends.setUser(user);
                list.add(friends);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * �����ҵĽ��� ֻ��ͨ���û����˲��ܲ���
     */

    // �����û�id��������н�����Ϣ
    public ArrayList<Friends> getAllMyFriends(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Friends> list = new ArrayList<Friends>();// ���Ѽ���

        try {
            conn = db.getConnection();
            String sql = "select * from friends where userId=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Friends friends = new Friends();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                friends.setId(rs.getInt("id"));
                friends.setPicture(serverImagesPath + rs.getString("picture"));
                friends.setOverview(rs.getString("overview"));
                friends.setWay(rs.getString("way"));
                friends.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                friends.setUser(user);
                list.add(friends);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ɾ��һ������
    public boolean deleteFriends(int friendsId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from friends where id=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, friendsId);
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
                return false;
            }
        }
    }

    // �޸Ľ�����Ϣ
    public boolean updateFriends(int friendsId, String[] update) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update friends set overview=? ,way=? ,contact=? ,picture=? where id=?";

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 0;
            for (; i < update.length; i++) {
                ps.setString(i + 1, update[i]);
            }
            ps.setInt(i + 1, friendsId);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                System.out.println(sql);
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
    // ��ӽ�����Ϣ
    public int addFriends(String[] update, int uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "insert into friends (overview,way,contact,picture,userId) value(?,?,?,?,?) ";// ��ӵ�sql���
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            for (; i < update.length; i++) {
                ps.setString(i + 1, update[i]);
            }
            ps.setInt(i + 1, uid);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
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
        return id;
    }

    //ͨ���������ƺ���ϵ��ʽ�жϽ����Ƿ����
    public boolean ifFriendsExist(String overview, String contact) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            String sql = "select *from friends where overview=? and contact =?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, overview);
            stmt.setString(2, contact);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    //��ȡ��ǰ���ݿ����һ���Ľ���id
    public int getLatestId() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            String sql = "select * from friends order by id DESC limit 1";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
