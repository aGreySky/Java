package pers.agreysky.idle.service.impl;

import org.springframework.stereotype.Service;

import pers.agreysky.idle.dao.AdviceDAO;
import pers.agreysky.idle.dao.UserDAO;
import pers.agreysky.idle.dto.Advice;
import pers.agreysky.idle.dto.User;
import pers.agreysky.idle.service.AdviceActionService;

@Service
public class AdviceActionServiceImpl implements AdviceActionService {
    private AdviceDAO advicedao;
    private UserDAO userdao;

    //添加建议
    @Override
    public boolean addAdvice(Advice ad) {
        return advicedao.addAdvice(ad);
    }
    //通过用户id查找用户
    @Override
    public User getUserByUserId(Integer id) {
        return userdao.getUserByUserId(id);
    }

    public AdviceDAO getAdvicedao() {
        return advicedao;
    }

    public void setAdvicedao(AdviceDAO advicedao) {
        this.advicedao = advicedao;
    }

    public UserDAO getUserdao() {
        return userdao;
    }

    public void setUserdao(UserDAO userdao) {
        this.userdao = userdao;
    }

}
