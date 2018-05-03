package per.agreysky.org.dao;

import per.agreysky.org.vo.Student;

public interface StudentDAO {
    //ͨ������֤�Ų���ѧ��
    public Student selectByReaderId(String readerId);
    //ѧ���¼�
    public void addStudent(Student stu);
    //ѧ��ɾ��
    public void deleteStudent(Student stu);
    //ѧ���޸�
    public void updateStudent(Student stu);
    //ѧ����¼
    public Student checkStudentLogin(String readerId, String password);

}
