package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Advice;
import pers.agreysky.idle.dto.User;

public interface AdviceActionService {
    //添加建议
    public boolean addAdvice(Advice ad);
    //通过用户id查找用户
    public User getUserByUserId(Integer id);

}
