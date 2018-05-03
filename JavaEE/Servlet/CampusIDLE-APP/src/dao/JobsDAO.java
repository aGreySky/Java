package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Items;
import entity.Jobs;
import entity.User;
import util.DBhelper;

public class JobsDAO {
    DBhelper db;
    public JobsDAO(DBhelper db) {
        this.db = db;
    }

    /*
     * �����м�ְ���еĲ��� ���е�¼���û����ܽ���
     */

    // ������м�ְ��Ϣ
    public ArrayList<Jobs> getAllJobs() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Jobs> list = new ArrayList<Jobs>();// ��ְ����
        try {
            conn = db.getConnection();
            String sql = "select *from jobs;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Jobs jobs = new Jobs();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                jobs.setId(rs.getInt("id"));
                jobs.setName(rs.getString("name"));
                jobs.setType(rs.getString("type"));
                jobs.setOverview(rs.getString("overview"));
                jobs.setWay(rs.getString("way"));
                jobs.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                jobs.setUser(user);
                list.add(jobs);
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

    // ���ݼ�ְ��Ż�ü�ְ����
    public Jobs getJobsById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Jobs jobs = new Jobs();
        try {
            conn = db.getConnection();
            String sql = "select *from jobs where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                jobs.setId(rs.getInt("id"));
                jobs.setName(rs.getString("name"));
                jobs.setType(rs.getString("type"));
                jobs.setOverview(rs.getString("overview"));
                jobs.setWay(rs.getString("way"));
                jobs.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                jobs.setUser(user);
            } else {
                jobs = null;
            }
            return jobs;
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

    // ���ݼ�ְ�����ü�ְ����
    public ArrayList<Jobs> getJobsByType(String type) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Jobs> list = new ArrayList<Jobs>();

        try {
            System.out.println(type);
            conn = db.getConnection();
            String sql = "select *from jobs where type=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Jobs jobs = new Jobs();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                jobs.setId(rs.getInt("id"));
                jobs.setName(rs.getString("name"));
                jobs.setType(rs.getString("type"));
                jobs.setOverview(rs.getString("overview"));
                jobs.setWay(rs.getString("way"));
                jobs.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                jobs.setUser(user);
                list.add(jobs);
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

    // ������ְ
    public ArrayList<Items> getJobsByUserCdn(String sql) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();// ��ְ����

        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Items jobs = new Items();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                jobs.setId(rs.getInt("id"));
                jobs.setName(rs.getString("name"));
                jobs.setType(rs.getString("type"));
                jobs.setOverview(rs.getString("overview"));
                jobs.setWay(rs.getString("way"));
                jobs.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                jobs.setUser(user);
                list.add(jobs);
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
     * �����ҵļ�ְ ֻ��ͨ���û����˲��ܲ���
     */

    // �����û�id��������м�ְ��Ϣ
    public ArrayList<Jobs> getAllMyJobs(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Jobs> list = new ArrayList<Jobs>();// ��ְ����

        try {
            conn = db.getConnection();
            String sql = "select * from jobs where userId=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Jobs jobs = new Jobs();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                jobs.setId(rs.getInt("id"));
                jobs.setName(rs.getString("name"));
                jobs.setType(rs.getString("type"));
                jobs.setOverview(rs.getString("overview"));
                jobs.setWay(rs.getString("way"));
                jobs.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                jobs.setUser(user);
                list.add(jobs);
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

    // ɾ��һ����ְ
    public boolean deleteJobs(int jobsId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from jobs where id=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, jobsId);
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

    // �޸ļ�ְ��Ϣ
    public boolean updateJobs(int jobsId, String[] update) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update jobs set name=? , overview=?, type=? ,way=? ,contact=?  where id=?";

        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            int i = 0;
            for (; i < update.length; i++) {
                ps.setString(i + 1, update[i]);
            }
            ps.setInt(i + 1, jobsId);
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
    // ��Ӽ�ְ��Ϣ
    public int addJobs(String[] update, int uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "insert into jobs (name,overview,type,way,contact,userId) value(?,?,?,?,?,?) ";// ��ӵ�sql���
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

    //ͨ����ְ���ƺ���ϵ��ʽ�жϼ�ְ�Ƿ����
    public boolean ifJobsExist(String name, String contact) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            String sql = "select *from jobs where name=? and contact =?;";
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
