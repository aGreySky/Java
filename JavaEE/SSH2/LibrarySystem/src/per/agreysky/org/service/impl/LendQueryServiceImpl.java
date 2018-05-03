package per.agreysky.org.service.impl;

import java.util.List;

import per.agreysky.org.dao.LendDAO;
import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.service.LendQueryService;
import per.agreysky.org.vo.Student;

public class LendQueryServiceImpl implements LendQueryService {
    private StudentDAO studentdao;
    private LendDAO lenddao;
    //查找学生
    @Override
    public Student selectByReaderId(String readerId) {
        return studentdao.selectByReaderId(readerId);
    }

    //分页查找所借阅的图书
    @Override
    public List selectLend(String readerId, int pageNow, int pageSize) {
        return lenddao.selectLend(readerId, pageNow, pageSize);
    }

    //查找所借阅图书的数量
    @Override
    public int selectLendSize(String readerId) {
        return lenddao.selectLendSize(readerId);
    }

    public LendDAO getLenddao() {
        return lenddao;
    }

    public void setLenddao(LendDAO lenddao) {
        this.lenddao = lenddao;
    }

    public StudentDAO getStudentdao() {
        return studentdao;
    }

    public void setStudentdao(StudentDAO studentdao) {
        this.studentdao = studentdao;
    }
}
