package per.agreysky.dao;
import per.agreysky.vo.Student;
public interface StudentDao {
    public Student selectStudent(String readerId); //查询读者信息
}
