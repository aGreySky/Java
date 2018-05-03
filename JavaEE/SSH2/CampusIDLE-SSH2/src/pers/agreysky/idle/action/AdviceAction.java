package pers.agreysky.idle.action;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import pers.agreysky.idle.dto.Advice;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.enums.ResultEnum;
import pers.agreysky.idle.exception.IdleException;
import pers.agreysky.idle.service.AdviceActionService;
import pers.agreysky.idle.util.ResultVOUtil;
import pers.agreysky.idle.vo.ResultVO;

/**
 * 用户建议url（advice）
 * 传入：user.id,advice.advice
 * 传出：成功 {"flag":"true"}
 *       失败 {"flag":"false"}
 * 
 * @author zzq
 *
 */
@Controller
public class AdviceAction extends ActionSupport {
    private Advice advice;
    private User user;
    private AdviceActionService adviceActionService;
    private ResultVO resultVO;

    @Override
    public String execute() throws IdleException, IOException {
        user = adviceActionService.getUserByUserId(user.getId());
        if (user == null) {
            throw new IdleException(ResultEnum.USER_NOT_EXIST);
        }
        advice.setUser(user);
        adviceActionService.addAdvice(advice);
        resultVO = ResultVOUtil.success();
        return SUCCESS;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public AdviceActionService getAdviceService() {
        return adviceActionService;
    }
    public void setAdviceActionService(
            AdviceActionService adviceActionService) {
        this.adviceActionService = adviceActionService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Advice getAdvice() {
        return advice;
    }

    public AdviceActionService getAdviceActionService() {
        return adviceActionService;
    }

    public ResultVO getResultVO() {
        return resultVO;
    }

    public void setResultVO(ResultVO resultVO) {
        this.resultVO = resultVO;
    }

}
