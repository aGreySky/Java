package pers.agreysky.idle.action;

import java.io.IOException;

import com.opensymphony.xwork2.ActionSupport;

import pers.agreysky.idle.dto.Jobs;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.enums.ResultEnum;
import pers.agreysky.idle.exception.IdleException;
import pers.agreysky.idle.service.DoMyJobsActionService;
import pers.agreysky.idle.util.ResultVOUtil;
import pers.agreysky.idle.vo.ResultVO;

public class DoMyJobsAction extends ActionSupport {
    private DoMyJobsActionService doMyJobsActionService;
    private Jobs job;
    private String action;
    private User user;
    private ResultVO resultVO;

    @Override
    public String execute() throws IOException {
        if (action.equals("add")) { // 添加我的兼职信息
            System.out.println("add");
            addAndUpdateMyJob();
        } else if (action.equals("delete")) { // 下架兼职信息
            System.out.println("delete");
            deleteMyJob();
        } else if (action.equals("update")) {// 修改兼职信息
            System.out.println("update");
            addAndUpdateMyJob();
        } else {
            throw new IdleException(ResultEnum.ACTION_ERROR);
        }
        resultVO = ResultVOUtil.success();
        return SUCCESS;

    }
    //删除我的一件兼职
    private void deleteMyJob() {
        doMyJobsActionService.deleteJobs(job);
        System.out.println("id:" + job.getId());
    }
    //添加、修改我的一件兼职
    private void addAndUpdateMyJob() {
        if (action.equals("add")) {
            if (doMyJobsActionService.ifJobsExist(//判断数据库中是否有该兼职
                    job.getOverview(), job.getContact())) {
                throw new IdleException(ResultEnum.JOB_EXIST);
            }
        }
        user = doMyJobsActionService.getUserByUserId(user.getId());
        if (user == null) {
            throw new IdleException(ResultEnum.USER_NOT_EXIST);
        }
        job.setUser(user);
        if (action.equals("add")) {
            doMyJobsActionService.addJobs(job);
        } else if (action.equals("update")) {
            doMyJobsActionService.updateJobs(job);
        }
    }
    public DoMyJobsActionService getDoMyJobsActionService() {
        return doMyJobsActionService;
    }
    public void setDoMyJobsActionService(
            DoMyJobsActionService doMyJobsActionService) {
        this.doMyJobsActionService = doMyJobsActionService;
    }
    public Jobs getJob() {
        return job;
    }
    public void setJob(Jobs job) {
        this.job = job;
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
