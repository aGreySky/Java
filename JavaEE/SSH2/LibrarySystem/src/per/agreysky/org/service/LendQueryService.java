package per.agreysky.org.service;

import java.util.List;

import per.agreysky.org.vo.Student;

public interface LendQueryService {

    //查找学生
    public Student selectByReaderId(String readerId);

    //分页查找所借阅的图书
    public List selectLend(String readerId, int pageNow, int pageSize);

    //查找所借阅图书的数量
    public int selectLendSize(String readerId);

}
