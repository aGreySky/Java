package pers.agreysky.idle.action;

import java.io.IOException;

import com.opensymphony.xwork2.ActionSupport;

import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.enums.ResultEnum;
import pers.agreysky.idle.exception.IdleException;
import pers.agreysky.idle.service.PersonDetailsActionService;
import pers.agreysky.idle.util.ResultVOUtil;
import pers.agreysky.idle.vo.PersonVO;
import pers.agreysky.idle.vo.ResultWithDataVO;
import pers.agreysky.idle.vo.UserInformVO;

/**
 * 对个人信息的操作，包含对“我的”信息的维护和对”TA的”信息的关注
 * 1、显示个人信息
 *  传入：user.id;
 *  传出：{
   * "person": {
     *   "helpList": [],
     *   "itemList": [],
    *    "user": {}
   * }
*}
 * @author zzq
 *
 */
public class PersonDetailsAction extends ActionSupport {
    private User user;
    private PersonDetailsActionService personDetailsActionService;
    private ResultWithDataVO resultVO;
    @Override
    public String execute() throws IOException {
        PersonVO personVO = new PersonVO();
        UserInformVO userInformVO = personDetailsActionService
                .getUserByUserId(user.getId());
        if (userInformVO == null) {
            throw new IdleException(ResultEnum.USER_NOT_EXIST);
        }
        personVO.setUser(userInformVO);
        personVO.setItemsList(
                personDetailsActionService.getItemListByUserId(user.getId()));
        personVO.setHelpsList(
                personDetailsActionService.getHelpListByUserId(user.getId()));
        resultVO = ResultVOUtil.success(personVO);
        return SUCCESS;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public PersonDetailsActionService getPersonDetailsActionService() {
        return personDetailsActionService;
    }
    public void setPersonDetailsActionService(
            PersonDetailsActionService personDetailsActionService) {
        this.personDetailsActionService = personDetailsActionService;
    }
    public ResultWithDataVO getResultVO() {
        return resultVO;
    }
    public void setResultVO(ResultWithDataVO resultVO) {
        this.resultVO = resultVO;
    }
}
