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
     * 对所有商品进行的操作
     * 所有登录的用户都能进行
     */

    //获得所有商品信息
    public ArrayList<Items> getAllItems() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();//商品集合
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

    //根据商品编号获得商品资料
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

    //搜索闲置
    public ArrayList<Items> getItemsByUserCdn(String sql) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();//商品集合
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

    //获得最近浏览的前五条商品信息
    public ArrayList<Items> getViewList(String list) {
        ArrayList<Items> itemlist = new ArrayList<Items>();
        int iCount = 3;//每次返回前3条记录
        if (list != null && list.length() > 0) {
            String[] arr = list.split("#");//split方法，用“#”分离字符串，组成数组

            if (arr.length >= iCount + 1) {//如果商品记录大于等于4条
                for (int i = arr.length - 2; i >= arr.length - iCount
                        - 1; i--) {//从数组末端(除去正浏览的)遍历iCount条记录
                    boolean flag = true;
                    for (int j = 0; j < arr.length - 2 - i; j++) {//为了不重复显示物品
                        if (arr[i].equals(arr[i + j + 1])) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        itemlist.add(getItemsById(Integer.parseInt(arr[i])));//将数组中数字对应的商品添加进集合
                    }
                }
            } else {//小于4条
                for (int i = arr.length - 2; i > 0; i--) {
                    boolean flag = true;
                    for (int j = 0; j < arr.length - 2 - i; j++) {//为了不重复显示物品
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
     * 操作我的闲置物品
     * 只有通过用户本人才能操作
     */

    //根据用户id获得其所有闲置物品信息
    public ArrayList<Items> getAllMyItems(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();//商品集合
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

    //删除一件闲置
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
    //删除一件闲置
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
    //修改闲置信息
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
    //添加闲置信息
    public int addGood(String[] update, int uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "insert into items (name,type,price,way,contact,overview,picture,userId) value(?,?,?,?,?,?,?,?) ";//添加的sql语句
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

    //通过商品名称和联系方式判断商品是否存在
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
