package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Jobs;
import pers.agreysky.idle.dto.User;

public interface DoMyJobsActionService {
    //ɾ��һ����ְ
    public boolean deleteJobs(Jobs job);
    //ͨ��id�����û�
    public User getUserByUserId(Integer id);
    //��Ӽ�ְ
    public boolean addJobs(Jobs job);
    //�޸�һ����ְ
    public boolean updateJobs(Jobs job);
    //�ж����ݿ����Ƿ��иü�ְ��Ϣ
    public boolean ifJobsExist(String overview, String contact);

}
