package pers.agreysky.idle.service.impl;

import pers.agreysky.idle.dao.JobsDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.Jobs;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.DoMyJobsActionService;

public class DoMyJobsActionServiceImpl implements DoMyJobsActionService {
    private JobsDAO jobsdao;
    private UserDAO userdao;
    //ɾ��һ����ְ
    @Override
    public boolean deleteJobs(Jobs job) {
        return jobsdao.deleteJobs(job);
    }
    //ͨ��id�����û�
    @Override
    public User getUserByUserId(Integer id) {
        return userdao.getUserByUserId(id);
    }
    //��Ӽ�ְ
    @Override
    public boolean addJobs(Jobs job) {
        return jobsdao.addJobs(job);
    }
    //�޸�һ����ְ
    @Override
    public boolean updateJobs(Jobs job) {
        return jobsdao.updateJobs(job);
    }
    //�ж����ݿ����Ƿ��иü�ְ��Ϣ
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
