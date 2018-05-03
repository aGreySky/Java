package per.agreysky.service.impl;
import per.agreysky.dao.StudentDao;
import per.agreysky.service.StudentService;
import per.agreysky.vo.Student;
public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao;
    public Student selectStudent(String readerId) {
        return studentDao.selectStudent(readerId);
    }
    public StudentDao getStudentDao() {
        return studentDao;
    }
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
