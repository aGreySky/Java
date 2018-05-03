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
        if (action.equals("add")) { // ����ҵļ�ְ��Ϣ
            System.out.println("add");
            addAndUpdateMyJob();
        } else if (action.equals("delete")) { // �¼ܼ�ְ��Ϣ
            System.out.println("delete");
            deleteMyJob();
        } else if (action.equals("update")) {// �޸ļ�ְ��Ϣ
            System.out.println("update");
            addAndUpdateMyJob();
        } else {
            throw new IdleException(ResultEnum.ACTION_ERROR);
        }
        resultVO = ResultVOUtil.success();
        return SUCCESS;

    }
    //ɾ���ҵ�һ����ְ
    private void deleteMyJob() {
        doMyJobsActionService.deleteJobs(job);
        System.out.println("id:" + job.getId());
    }
    //��ӡ��޸��ҵ�һ����ְ
    private void addAndUpdateMyJob() {
        if (action.equals("add")) {
            if (doMyJobsActionService.ifJobsExist(//�ж����ݿ����Ƿ��иü�ְ
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
