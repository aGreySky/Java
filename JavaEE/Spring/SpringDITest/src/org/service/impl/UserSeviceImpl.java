package org.service.impl;

import org.service.UserService;
import org.vo.User;

public class UserSeviceImpl implements UserService {

    @Override
    public void save(User user) {
        System.out.println("这是用户保存方法");
    }

}
