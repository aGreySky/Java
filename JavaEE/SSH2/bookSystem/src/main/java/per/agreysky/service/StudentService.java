package per.agreysky.service;
import per.agreysky.vo.Student;
public interface StudentService {
    //查询读者信息
    public Student selectStudent(String readerId);
}
