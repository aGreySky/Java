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
 * �Ը�����Ϣ�Ĳ����������ԡ��ҵġ���Ϣ��ά���Ͷԡ�TA�ġ���Ϣ�Ĺ�ע
 * 1����ʾ������Ϣ
 *  ���룺user.id;
 *  ������{
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
