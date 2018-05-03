package pers.agreysky.idle.dao;

import java.util.ArrayList;

import pers.agreysky.idle.dto.Jobs;
import pers.agreysky.idle.vo.AllEntityVO;

public interface JobsDAO {
    //ɾ��һ����ְ
    public boolean deleteJobs(Jobs job);
    //��Ӽ�ְ
    public boolean addJobs(Jobs job);
    //�޸�һ����ְ
    public boolean updateJobs(Jobs job);
    //�ṩ���Ͳ��Ҽ�ְ
    public ArrayList<Jobs> getJobsByType(String type);
    //ͨ���������Ҽ�ְ
    public ArrayList<AllEntityVO> getJobsByUserCdn(String jobsSql);
    //�ж����ݿ����Ƿ��иü�ְ��Ϣ
    public boolean ifJobsExist(String overview, String contact);
}
