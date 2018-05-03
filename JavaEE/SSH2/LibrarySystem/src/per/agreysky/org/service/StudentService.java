package per.agreysky.org.service;

import per.agreysky.org.vo.Student;

public interface StudentService {

    //查找学生
    public Student selectByReaderId(String readerId);
    //添加学生
    public void addStudent(Student stu);
    //删除学生
    public void deleteStudent(Student stu);
    //修改学生
    public void updateStudent(Student stu);

}
