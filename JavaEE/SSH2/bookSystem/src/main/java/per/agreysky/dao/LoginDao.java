package per.agreysky.dao;
import per.agreysky.vo.Login;
public interface LoginDao {
    //��ѯ��Ϣ
    public Login checkLogin(String name, String password);
}
