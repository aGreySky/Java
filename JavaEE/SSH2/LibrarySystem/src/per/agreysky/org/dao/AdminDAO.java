package per.agreysky.org.dao;

import per.agreysky.org.vo.Admin;

public interface AdminDAO {
    //����Ա��¼
    public Admin checkAdminLogin(String adminId, String password);
}
