package per.agreysky.org.service.impl;

import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.service.StudentService;
import per.agreysky.org.vo.Student;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentdao;

    //查找学生
    @Override
    public Student selectByReaderId(String readerId) {
        return studentdao.selectByReaderId(readerId);
    }
    //添加学生
    @Override
    public void addStudent(Student stu) {
        studentdao.addStudent(stu);
    }
    //删除学生
    @Override
    public void deleteStudent(Student stu) {
        studentdao.deleteStudent(stu);
    }
    //修改学生
    @Override
    public void updateStudent(Student stu) {
        studentdao.updateStudent(stu);
    }
    public StudentDAO getStudentdao() {
        return studentdao;
    }

    public void setStudentdao(StudentDAO studentdao) {
        this.studentdao = studentdao;
    }

}
