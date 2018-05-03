package per.agreysky.service;

import java.util.List;

import per.agreysky.bean.Student;
import per.agreysky.dao.DemoJdbcDao;

public class StudentActionService {
    private DemoJdbcDao dao = new DemoJdbcDao();

    public List<Student> queryAllStudents() {
        return dao.queryAllStudents();
    }

    public void delStudent(Student stud) {
        dao.delStudent(stud);
    }

    public void addStudent(Student stud) {
        dao.addStudent(stud);
    }

    public List<Student> queryStudents(Student stud) {
        return dao.queryStudents(stud);
    }

}