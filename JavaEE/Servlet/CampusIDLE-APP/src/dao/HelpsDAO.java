package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Helps;
import entity.Items;
import entity.User;
import util.DBhelper;

public class HelpsDAO {
    DBhelper db;
    public HelpsDAO(DBhelper db) {
        this.db = db;
    }

    /*
     * �����а������еĲ��� ���е�¼���û����ܽ���
     */

    // ������а�����Ϣ
    public ArrayList<Helps> getAllHelps() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Helps> list = new ArrayList<Helps>();// ��������
        try {
            conn = db.getConnection();
            String sql = "select *from helps;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Helps helps = new Helps();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                helps.setId(rs.getInt("id"));
                helps.setName(rs.getString("name"));
                helps.setType(rs.getString("type"));
                helps.setOverview(rs.getString("overview"));
                helps.setWay(rs.getString("way"));
                helps.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                helps.setUser(user);
                list.add(helps);
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

    // ���ݰ�����Ż�ð�������
    public Helps getHelpsById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Helps helps = new Helps();
        try {
            conn = db.getConnection();
            String sql = "select *from helps where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                helps.setId(rs.getInt("id"));
                helps.setName(rs.getString("name"));
                helps.setType(rs.getString("type"));
                helps.setOverview(rs.getString("overview"));
                helps.setWay(rs.getString("way"));
                helps.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                helps.setUser(user);
            } else {
                helps = null;
            }
            return helps;
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

    // ���ݰ��������ð�������
    public ArrayList<Helps> getHelpsByType(String type) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Helps> list = new ArrayList<Helps>();

        try {
            System.out.println(type);
            conn = db.getConnection();
            String sql = "select *from helps where type=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Helps helps = new Helps();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                helps.setId(rs.getInt("id"));
                helps.setName(rs.getString("name"));
                helps.setType(rs.getString("type"));
                helps.setOverview(rs.getString("overview"));
                helps.setWay(rs.getString("way"));
                helps.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                helps.setUser(user);
                list.add(helps);
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
    public ArrayList<Items> getHelpsByUserCdn(String sql) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();// ��Ʒ����

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Items helps = new Items();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                helps.setId(rs.getInt("id"));
                helps.setName(rs.getString("name"));
                helps.setType(rs.getString("type"));
                helps.setOverview(rs.getString("overview"));
                helps.setWay(rs.getString("way"));
                helps.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                helps.setUser(user);
                list.add(helps);
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
     * �����ҵİ��� ֻ��ͨ���û����˲��ܲ���
     */

    // �����û�id��������а�����Ϣ
    public ArrayList<Helps> getAllMyHelps(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Helps> list = new ArrayList<Helps>();// ��������

        try {
            conn = db.getConnection();
            String sql = "select * from helps where userId=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Helps helps = new Helps();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                helps.setId(rs.getInt("id"));
                helps.setName(rs.getString("name"));
                helps.setType(rs.getString("type"));
                helps.setOverview(rs.getString("overview"));
                helps.setWay(rs.getString("way"));
                helps.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                helps.setUser(user);
                list.add(helps);
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
    public boolean deleteHelps(int helpId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from helps where id=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, helpId);
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

    // �޸İ�����Ϣ
    public boolean updateHelps(int helpsId, String[] update) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update helps set name=? , overview=?, type=? ,way=? ,contact=?  where id=?";

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 0;
            for (; i < update.length; i++) {
                ps.setString(i + 1, update[i]);
            }
            ps.setInt(i + 1, helpsId);
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
    // ��Ӱ�����Ϣ
    public int addHelps(String[] update, int uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "insert into helps (name,overview,type,way,contact,userId) value(?,?,?,?,?,?) ";// ��ӵ�sql���
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

    //ͨ���������ƺ���ϵ��ʽ�жϰ����Ƿ����
    public boolean ifHelpExist(String name, String contact) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            String sql = "select *from helps where name=? and contact =?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
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

}
