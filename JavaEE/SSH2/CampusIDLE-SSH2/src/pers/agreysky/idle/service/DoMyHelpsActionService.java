package pers.agreysky.idle.service;

import pers.agreysky.idle.dto.Helps;
import pers.agreysky.idle.dto.User;

public interface DoMyHelpsActionService {
    //删除一件我的帮助
    public boolean deleteHelps(Helps help);
    //通过id查找用户
    public User getUserByUserId(Integer id);
    //添加一件我的帮助
    public boolean addHelps(Helps help);
    //修改一件我的帮助
    public boolean updateHelps(Helps help);
    //查找一件我的帮助
    public Helps getHelpsById(Integer id);
    //判断帮助信息是否存在
    public boolean ifHelpsExist(String overview, String contact);

}
