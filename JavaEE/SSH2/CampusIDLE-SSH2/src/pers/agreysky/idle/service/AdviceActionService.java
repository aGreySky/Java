package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Advice;
import pers.agreysky.idle.dto.User;

public interface AdviceActionService {
    //��ӽ���
    public boolean addAdvice(Advice ad);
    //ͨ���û�id�����û�
    public User getUserByUserId(Integer id);

}
