package per.agreysky.org.service;

import per.agreysky.org.vo.Student;

public interface StudentService {

    //����ѧ��
    public Student selectByReaderId(String readerId);
    //���ѧ��
    public void addStudent(Student stu);
    //ɾ��ѧ��
    public void deleteStudent(Student stu);
    //�޸�ѧ��
    public void updateStudent(Student stu);

}
