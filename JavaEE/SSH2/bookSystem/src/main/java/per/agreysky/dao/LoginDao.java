package per.agreysky.dao;
import per.agreysky.vo.Login;
public interface LoginDao {
    //≤È—Ø–≈œ¢
    public Login checkLogin(String name, String password);
}
