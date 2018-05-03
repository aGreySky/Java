package pers.agreysky.idle.action;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensymphony.xwork2.ActionSupport;

import pers.agreysky.idle.dto.Helps;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.enums.ResultEnum;
import pers.agreysky.idle.exception.IdleException;
import pers.agreysky.idle.service.DoMyHelpsActionService;
import pers.agreysky.idle.util.ResultVOUtil;
import pers.agreysky.idle.vo.ResultVO;

public class DoMyHelpsAction extends ActionSupport {
    private Helps help;
    private DoMyHelpsActionService doMyHelpsActionService;
    private String action;
    private User user;
    private ResultVO resultVO;

    @Override
    public String execute() throws IOException {
        if (action.equals("add")) { // ����ҵİ�����Ϣ
            System.out.println("add");
            addAndUpdateMyHelp();
        } else if (action.equals("delete")) { // �¼ܰ�����Ϣ
            System.out.println("delete");
            deleteMyHelp();
        } else if (action.equals("update")) {// �޸İ�����Ϣ
            System.out.println("update");
            addAndUpdateMyHelp();
        } else {
            throw new IdleException(ResultEnum.ACTION_ERROR);
        }
        resultVO = ResultVOUtil.success();
        return SUCCESS;

    }
    //ɾ���ҵ�һ������
    private void deleteMyHelp() {
        help = doMyHelpsActionService.getHelpsById(help.getId());
        if (help == null) {
            throw new IdleException(ResultEnum.HELP_NOT_EXIST);
        }
        doMyHelpsActionService.deleteHelps(help);//ɾ�����ݿ���Ϣ
        System.out.println("id:" + help.getId());
    }
    //��ӡ��޸��ҵ�һ������
    private void addAndUpdateMyHelp() {
        if (action.equals("add")) {
            if (doMyHelpsActionService.ifHelpsExist(//�ж����ݿ����Ƿ��и�
                    help.getOverview(), help.getContact())) {
                throw new IdleException(ResultEnum.HELP_EXISTED);
            }
        }
        Pattern p = Pattern.compile(
                "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$");
        Matcher m = p.matcher(help.getContact());
        if (m.matches()) {
            help.setWay("�ֻ�");
        } else {
            help.setWay("QQ");
        }
        user = doMyHelpsActionService.getUserByUserId(user.getId());
        if (user == null) {
            throw new IdleException(ResultEnum.USER_NOT_EXIST);
        }
        help.setUser(user);
        if (action.equals("add")) {
            doMyHelpsActionService.addHelps(help);//�������ݿ�
        } else if (action.equals("update")) {
            doMyHelpsActionService.updateHelps(help);//�������ݿ�
        }
    }

    public Helps getHelp() {
        return help;
    }
    public void setHelp(Helps help) {
        this.help = help;
    }
    public DoMyHelpsActionService getDoMyHelpsActionService() {
        return doMyHelpsActionService;
    }
    public void setDoMyHelpsActionService(
            DoMyHelpsActionService doMyHelpsActionService) {
        this.doMyHelpsActionService = doMyHelpsActionService;
    }
    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public ResultVO getResultVO() {
        return resultVO;
    }
    public void setResultVO(ResultVO resultVO) {
        this.resultVO = resultVO;
    }

}
