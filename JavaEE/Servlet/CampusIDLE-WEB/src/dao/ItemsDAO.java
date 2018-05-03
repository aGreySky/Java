package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Items;
import entity.User;
import util.DBhelper;

public class ItemsDAO {
    DBhelper db;
    public ItemsDAO(DBhelper db) {
        this.db = db;
    }

    /*
     * ��������Ʒ���еĲ���
     * ���е�¼���û����ܽ���
     */

    //���������Ʒ��Ϣ
    public ArrayList<Items> getAllItems() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();//��Ʒ����
        try {
            conn = db.getConnection();
            String sql = "select *from items;";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Items item = new Items();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setPicture(rs.getString("picture"));
                item.setType(rs.getString("type"));
                item.setOverview(rs.getString("overview"));
                item.setWay(rs.getString("way"));
                item.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                item.setUser(user);
                list.add(item);
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

    //������Ʒ��Ż����Ʒ����
    public Items getItemsById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Items item = new Items();
        try {
            conn = db.getConnection();
            String sql = "select *from items where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setPicture(rs.getString("picture"));
                item.setType(rs.getString("type"));
                item.setOverview(rs.getString("overview"));
                item.setWay(rs.getString("way"));
                item.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                item.setUser(user);
            }
            return item;
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

    //��������
    public ArrayList<Items> getItemsByUserCdn(String sql) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();//��Ʒ����
        try {
            conn = db.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Items item = new Items();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setPicture(rs.getString("picture"));
                item.setType(rs.getString("type"));
                item.setOverview(rs.getString("overview"));
                item.setWay(rs.getString("way"));
                item.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                item.setUser(user);
                list.add(item);
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

    //�����������ǰ������Ʒ��Ϣ
    public ArrayList<Items> getViewList(String list) {
        ArrayList<Items> itemlist = new ArrayList<Items>();
        int iCount = 3;//ÿ�η���ǰ3����¼
        if (list != null && list.length() > 0) {
            String[] arr = list.split("#");//split�������á�#�������ַ������������

            if (arr.length >= iCount + 1) {//�����Ʒ��¼���ڵ���4��
                for (int i = arr.length - 2; i >= arr.length - iCount
                        - 1; i--) {//������ĩ��(��ȥ�������)����iCount����¼
                    boolean flag = true;
                    for (int j = 0; j < arr.length - 2 - i; j++) {//Ϊ�˲��ظ���ʾ��Ʒ
                        if (arr[i].equals(arr[i + j + 1])) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        itemlist.add(getItemsById(Integer.parseInt(arr[i])));//�����������ֶ�Ӧ����Ʒ��ӽ�����
                    }
                }
            } else {//С��4��
                for (int i = arr.length - 2; i > 0; i--) {
                    boolean flag = true;
                    for (int j = 0; j < arr.length - 2 - i; j++) {//Ϊ�˲��ظ���ʾ��Ʒ
                        if (arr[i].equals(arr[i + j + 1])) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        itemlist.add(getItemsById(Integer.parseInt(arr[i])));
                    }
                }
            }

            return itemlist;
        } else {
            return null;
        }
    }

    /*
     * �����ҵ�������Ʒ
     * ֻ��ͨ���û����˲��ܲ���
     */

    //�����û�id���������������Ʒ��Ϣ
    public ArrayList<Items> getAllMyItems(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();//��Ʒ����
        try {
            conn = db.getConnection();
            String sql = "select * from items where userId=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Items item = new Items();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setPicture(rs.getString("picture"));
                item.setType(rs.getString("type"));
                item.setOverview(rs.getString("overview"));
                item.setWay(rs.getString("way"));
                item.setContact(rs.getString("contact"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                item.setUser(user);
                list.add(item);
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

    //ɾ��һ������
    public boolean deleteGood(int itemId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from items where id=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, itemId);
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
    //ɾ��һ������
    public boolean deleteAllMyGoodByUid(int uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "delete from items where userId=?";
        try {
            conn = db.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, uid);
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
    //�޸�������Ϣ
    public boolean updateGood(int itemId, String sql, String[] update) {
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
            ps.setInt(i + 1, itemId);
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
    //���������Ϣ
    public int addGood(String[] update, int uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "insert into items (name,type,price,way,contact,overview,picture,userId) value(?,?,?,?,?,?,?,?) ";//��ӵ�sql���
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

    //ͨ����Ʒ���ƺ���ϵ��ʽ�ж���Ʒ�Ƿ����
    public boolean ifItemExist(String name, String contact) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            String sql = "select *from items where name=? and contact =?;";
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
