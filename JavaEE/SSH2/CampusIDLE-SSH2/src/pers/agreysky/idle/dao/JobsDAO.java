package pers.agreysky.idle.dao;

import java.util.ArrayList;

import pers.agreysky.idle.dto.Jobs;
import pers.agreysky.idle.vo.AllEntityVO;

public interface JobsDAO {
    //删除一件兼职
    public boolean deleteJobs(Jobs job);
    //添加兼职
    public boolean addJobs(Jobs job);
    //修改一件兼职
    public boolean updateJobs(Jobs job);
    //提供类型查找兼职
    public ArrayList<Jobs> getJobsByType(String type);
    //通过条件查找兼职
    public ArrayList<AllEntityVO> getJobsByUserCdn(String jobsSql);
    //判断数据库中是否有该兼职信息
    public boolean ifJobsExist(String overview, String contact);
}
