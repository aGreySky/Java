package per.agreysky.org.dao;

import per.agreysky.org.vo.Admin;

public interface AdminDAO {
    //¹ÜÀíÔ±µÇÂ¼
    public Admin checkAdminLogin(String adminId, String password);
}
