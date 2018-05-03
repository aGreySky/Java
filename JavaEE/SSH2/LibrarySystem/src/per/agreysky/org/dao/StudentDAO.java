package per.agreysky.org.dao;

import per.agreysky.org.vo.Student;

public interface StudentDAO {
    //通过借书证号查找学生
    public Student selectByReaderId(String readerId);
    //学生新加
    public void addStudent(Student stu);
    //学生删除
    public void deleteStudent(Student stu);
    //学生修改
    public void updateStudent(Student stu);
    //学生登录
    public Student checkStudentLogin(String readerId, String password);

}
