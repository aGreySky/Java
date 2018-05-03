package per.agreysky.service.impl;

import per.agreysky.dao.LoginDao;
import per.agreysky.service.LoginService;
import per.agreysky.vo.Login;

public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao;
    @Override
    public Login checkLogin(String username, String password) {
        return loginDao.checkLogin(username, password);
    }
    public LoginDao getLoginDao() {
        return loginDao;
    }
    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
}
