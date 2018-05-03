package per.agreysky.service;
import per.agreysky.vo.Login;
public interface LoginService {
    public Login checkLogin(String username, String password);
}
