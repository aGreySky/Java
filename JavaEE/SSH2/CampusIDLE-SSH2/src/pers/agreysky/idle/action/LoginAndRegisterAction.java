package pers.agreysky.idle.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;

import com.opensymphony.xwork2.ActionSupport;

import pers.agreysky.idle.constant.StateConstant;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.enums.ResultEnum;
import pers.agreysky.idle.exception.IdleException;
import pers.agreysky.idle.service.LoginAndRegisterActionService;
import pers.agreysky.idle.util.ResultVOUtil;
import pers.agreysky.idle.util.SHA;
import pers.agreysky.idle.vo.UserVO;

/**
 * �û���¼��ע��
 * 
 *1.��¼��url:loginregister?method=login��
 *     ���룺user.username��user.password
 *     �������ɹ� {"userId":1,"flag":"true"}
 *           ʧ�� {"flag":"false"}
 * 2.ע�ᣨurl:loginregister?method=register��
 *     ���룺user.username��user.password��user.phone
 *     �������ɹ� {"flag":"true"}
 *           ʧ�� {"flag":"false"}
 * @author zzq
 *
 */
public class LoginAndRegisterAction extends ActionSupport {
    private User user;
    private LoginAndRegisterActionService loginAndRegisterActionService;
    private String method;
    private Object resultVO;

    @Override
    public String execute() throws IOException, IllegalAccessException,
            InvocationTargetException {
        String method = this.getMethod();
        if (method.equals("login")) {
            user.setPassword(SHA.encrypt(user.getPassword()));
            Pattern p = Pattern.compile(
                    "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
            Matcher m = p.matcher(user.getUsername());
            if (m.matches()) {
                System.out.println("�ֻ���");
                user.setPhone(user.getPhone());
                user = loginAndRegisterActionService.ifRightByPhone(user);
                if (user == null) {
                    throw new IdleException(ResultEnum.LOGIN_PHONE_FAIL);
                }
            } else if (Pattern.compile(
                    "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})")
                    .matcher(user.getUsername()).matches()) {
                System.out.println("����");
                user.setUseremail(user.getUsername());
                user = loginAndRegisterActionService.ifRightByUseremail(user);
                if (user == null) {
                    throw new IdleException(ResultEnum.LOGIN_USEREMAIL_FAIL);
                }
            } else {
                System.out.println("�û���");
                user.setUsername(user.getUsername());
                user = loginAndRegisterActionService.ifRightByUsername(user);
                if (user == null) {
                    throw new IdleException(ResultEnum.LOGIN_USERNAME_FAIL);
                }
            }
            if (!user.getState().equals(StateConstant.ACTIVATION)) {
                throw new IdleException(ResultEnum.NON_ACTIVATION);
            }
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            setResultVO(ResultVOUtil.success(userVO));
            return SUCCESS;
        } else if (method.equals("register")) {
            user.setUsername(user.getUsername());
            user.setPassword(SHA.encrypt(user.getPassword()));
            user.setPhone(user.getPhone());
            user.setState("1");
            user.setItemsNum("0");
            if (loginAndRegisterActionService.ifExist(user)) {//�ж��û����Ƿ����
                throw new IdleException(ResultEnum.USERNAME_EXIST);
            }
            loginAndRegisterActionService.addUser(user);
            setResultVO(ResultVOUtil.success());
            return SUCCESS;
        } else {
            throw new IdleException(ResultEnum.ACTION_ERROR);
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LoginAndRegisterActionService getLoginAndRegisterActionService() {
        return loginAndRegisterActionService;
    }

    public void setLoginAndRegisterActionService(
            LoginAndRegisterActionService loginAndRegisterActionService) {
        this.loginAndRegisterActionService = loginAndRegisterActionService;
    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getResultVO() {
        return resultVO;
    }

    public void setResultVO(Object resultVO) {
        this.resultVO = resultVO;
    }

}
