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
    private String serverImagesPath = "http://xiaoyou.free.ngrok.cc/CampusIDLE-WEB/images/itemsPicture/";
    DBhelper db;
    public ItemsDAO(DBhelper db) {
        this.db = db;
    }

    /*
     * 对所有商品进行的操作 所有登录的用户都能进行
     */

    // 获得所有商品信息
    public ArrayList<Items> getAllItems() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();// 商品集合
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
                item.setPicture(serverImagesPath + rs.getString("picture"));
                item.setType(rs.getString("type"));
                item.setOverview(rs.getString("overview"));
                item.setWay(rs.getString("way"));
                item.setContact(rs.getString("contact"));
                item.setIfNew(rs.getString("ifNew"));
                item.setOrigPrice(rs.getString("origPrice"));
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

    // 根据商品编号获得商品资料
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
                item.setPicture(serverImagesPath + rs.getString("picture"));
                item.setType(rs.getString("type"));
                item.setOverview(rs.getString("overview"));
                item.setWay(rs.getString("way"));
                item.setContact(rs.getString("contact"));
                item.setIfNew(rs.getString("ifNew"));
                item.setOrigPrice(rs.getString("origPrice"));
                user = userdao.getUserByUserId(rs.getInt("userId"));
                item.setUser(user);
            } else {
                item = null;
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

    // 根据商品种类获得商品资料
    public ArrayList<Items> getItemsByType(String type) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();

        try {
            conn = db.getConnection();
            String sql = "select *from items where type=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Items item = new Items();
                User user = new User();
                UserDAO userdao = new UserDAO(db);
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setPrice(rs.getString("price"));
                item.setPicture(serverImagesPath + rs.getString("picture"));
                item.setType(rs.getString("type"));
                item.setOverview(rs.getString("overview"));
                item.setWay(rs.getString("way"));
                item.setContact(rs.getString("contact"));
                item.setIfNew(rs.getString("ifNew"));
                item.setOrigPrice(rs.getString("origPrice"));
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
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // 搜索闲置
    public ArrayList<Items> getItemsByUserCdn(String sql) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();// 商品集合

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
                item.setPicture(serverImagesPath + rs.getString("picture"));
                item.setType(rs.getString("type"));
                item.setOverview(rs.getString("overview"));
                item.setWay(rs.getString("way"));
                item.setContact(rs.getString("contact"));
                item.setIfNew(rs.getString("ifNew"));
                item.setOrigPrice(rs.getString("origPrice"));
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

    //获取当前数据库最后一件的商品id
    public int getLatestId() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = db.getConnection();
            String sql = "select * from items order by id DESC limit 1";
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

    /*
     * 操作我的闲置物品 只有通过用户本人才能操作
     */

    // 根据用户id获得其所有闲置物品信息
    public ArrayList<Items> getAllMyItems(int userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Items> list = new ArrayList<Items>();// 商品集合
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
                item.setPicture(serverImagesPath + rs.getString("picture"));
                item.setType(rs.getString("type"));
                item.setOverview(rs.getString("overview"));
                item.setWay(rs.getString("way"));
                item.setContact(rs.getString("contact"));
                item.setIfNew(rs.getString("ifNew"));
                item.setOrigPrice(rs.getString("origPrice"));
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

    // 删除一件闲置
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

    // 修改闲置信息
    public boolean updateGood(String[] update, int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "update items set name=? ,price=? , overview=?, type=? ,way=? ,contact=? ,ifNew=? ,origPrice ,picture=? where id=?";
        System.out.println(sql);
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
    // 添加闲置信息
    public int addGood(String[] update, int uid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        String sql = "insert into items (name,price,overview,type,way,contact,ifNew,origPrice,picture,userId) value(?,?,?,?,?,?,?,?,?,?) ";// 添加的sql语句
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
