package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Jobs;
import pers.agreysky.idle.dto.User;

public interface DoMyJobsActionService {
    //删除一件兼职
    public boolean deleteJobs(Jobs job);
    //通过id查找用户
    public User getUserByUserId(Integer id);
    //添加兼职
    public boolean addJobs(Jobs job);
    //修改一件兼职
    public boolean updateJobs(Jobs job);
    //判断数据库中是否有该兼职信息
    public boolean ifJobsExist(String overview, String contact);

}
