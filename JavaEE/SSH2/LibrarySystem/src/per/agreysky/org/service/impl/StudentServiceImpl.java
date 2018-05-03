package per.agreysky.org.service.impl;

import per.agreysky.org.dao.StudentDAO;
import per.agreysky.org.service.StudentService;
import per.agreysky.org.vo.Student;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentdao;

    //����ѧ��
    @Override
    public Student selectByReaderId(String readerId) {
        return studentdao.selectByReaderId(readerId);
    }
    //���ѧ��
    @Override
    public void addStudent(Student stu) {
        studentdao.addStudent(stu);
    }
    //ɾ��ѧ��
    @Override
    public void deleteStudent(Student stu) {
        studentdao.deleteStudent(stu);
    }
    //�޸�ѧ��
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
