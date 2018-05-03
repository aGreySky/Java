package pers.agreysky.idle.dao;

import java.util.ArrayList;
import java.util.List;

import pers.agreysky.idle.dto.Helps;
import pers.agreysky.idle.vo.AllEntityVO;

public interface HelpsDAO {
    //删除一件我的帮助
    public boolean deleteHelps(Helps help);
    //添加一件我的帮助
    public boolean addHelps(Helps help);
    //修改一件我的帮助
    public boolean updateHelps(Helps help);
    //通过类型查询帮助
    public ArrayList<Helps> getHelpsByType(String type);
    //通过条件查找帮助
    public ArrayList<AllEntityVO> getHelpsByUserCdn(String helpsSql);
    //通过id查找帮助
    public Helps getHelpsById(Integer id);
    //通过用户id查找帮助信息
    public List getHelpListByUserId(Integer id);
    //判断帮助是否存在
    public boolean ifHelpsExist(String overview, String contact);
}
