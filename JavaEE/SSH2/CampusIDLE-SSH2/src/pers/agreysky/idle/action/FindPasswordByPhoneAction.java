package pers.agreysky.idle.action;

import java.io.IOException;

import com.opensymphony.xwork2.ActionSupport;

import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.enums.ResultEnum;
import pers.agreysky.idle.exception.IdleException;
import pers.agreysky.idle.service.FindPasswordByPhoneActionService;
import pers.agreysky.idle.util.ResultVOUtil;
import pers.agreysky.idle.util.SHA;
import pers.agreysky.idle.vo.ResultVO;

public class FindPasswordByPhoneAction extends ActionSupport {
    private User user;
    private FindPasswordByPhoneActionService findPasswordByPhoneActionService;
    private String action;
    private ResultVO resultVO;

    @Override
    public String execute() throws IOException {
        if (action.equals("firstStep")) {
            if (findPasswordByPhoneActionService
                    .getUserByPhone(user.getPhone()) == null) {
                throw new IdleException(ResultEnum.PHONE_NOT_EXIST);
            }
        } else if (action.equals("secondStep")) {
            User newUser = findPasswordByPhoneActionService
                    .getUserByPhone(user.getPhone());
            if (newUser == null) {
                throw new IdleException(ResultEnum.USER_NOT_EXIST);
            }
            newUser.setPassword(SHA.encrypt(user.getPassword()));
            findPasswordByPhoneActionService.updateUser(newUser);
        } else {
            throw new IdleException(ResultEnum.ACTION_ERROR);
        }
        resultVO = ResultVOUtil.success();
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public FindPasswordByPhoneActionService getFindPasswordByPhoneActionService() {
        return findPasswordByPhoneActionService;
    }
    public void setFindPasswordByPhoneActionService(
            FindPasswordByPhoneActionService findPasswordByPhoneActionService) {
        this.findPasswordByPhoneActionService = findPasswordByPhoneActionService;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public ResultVO getResultVO() {
        return resultVO;
    }

    public void setResultVO(ResultVO resultVO) {
        this.resultVO = resultVO;
    }
}
