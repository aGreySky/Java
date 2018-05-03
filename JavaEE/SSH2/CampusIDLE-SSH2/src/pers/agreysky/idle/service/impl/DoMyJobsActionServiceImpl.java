package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.JobsDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.Jobs;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.DoMyJobsActionService;

public class DoMyJobsActionServiceImpl implements DoMyJobsActionService {
    private JobsDAO jobsdao;
    private UserDAO userdao;
    //删除一件兼职
    @Override
    public boolean deleteJobs(Jobs job) {
        return jobsdao.deleteJobs(job);
    }
    //通过id查找用户
    @Override
    public User getUserByUserId(Integer id) {
        return userdao.getUserByUserId(id);
    }
    //添加兼职
    @Override
    public boolean addJobs(Jobs job) {
        return jobsdao.addJobs(job);
    }
    //修改一件兼职
    @Override
    public boolean updateJobs(Jobs job) {
        return jobsdao.updateJobs(job);
    }
    //判断数据库中是否有该兼职信息
    @Override
    public boolean ifJobsExist(String overview, String contact) {
        return jobsdao.ifJobsExist(overview, contact);
    }
    public JobsDAO getJobsdao() {
        return jobsdao;
    }
    public void setJobsdao(JobsDAO jobsdao) {
        this.jobsdao = jobsdao;
    }
    public UserDAO getUserdao() {
        return userdao;
    }
    public void setUserdao(UserDAO userdao) {
        this.userdao = userdao;
    }

}
