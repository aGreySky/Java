package per.agreysky.org.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import per.agreysky.org.service.StudentService;
import per.agreysky.org.util.SHA;
import per.agreysky.org.vo.Student;

public class StudentAction extends ActionSupport {
    private String message;
    private File photo;
    private Student student;
    private StudentService studentService;

    //ѧ���¼�
    public String addStudent() throws IOException {
        Student stu = studentService.selectByReaderId(student.getReaderId());
        if (stu != null) {
            this.setMessage("�ý���֤���Ѵ��ڣ������ظ����");
            return SUCCESS;
        } else if (student.getPassword() == null
                || student.getPassword().equals("********")) {
            this.setMessage("���벻��Ϊ��");
            return SUCCESS;
        } else {
            stu = new Student();
            stu.setReaderId(student.getReaderId());
            stu.setName(student.getName());
            if (!student.getPassword().equals("********")) {
                stu.setPassword(SHA.encrypt(student.getPassword()));
            }
            stu.setBorn(student.getBorn());
            stu.setSex(student.isSex());
            stu.setSpec(student.getSpec());
            if (this.getPhoto() != null) {
                FileInputStream fis = new FileInputStream(this.getPhoto());
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                stu.setPhoto(buffer);
            }
            studentService.addStudent(stu);
            this.setMessage("��ӳɹ���");
            return SUCCESS;
        }
    }

    //ѧ��ɾ��
    public String deleteStudent() {
        if (student.getReaderId().equals("")) {
            this.setMessage("���������֤��!");
            return SUCCESS;
        } else {
            Student stu = studentService
                    .selectByReaderId(student.getReaderId());
            if (stu == null) {
                this.setMessage("�����ڴ˽���֤�ţ�");
                return SUCCESS;
            } else if (stu.getNum() != 0) {
                this.setMessage("��ѧ������δ�黹ͼ�飬����ɾ����");
                return SUCCESS;
            } else {
                studentService.deleteStudent(stu);
                this.setMessage("ɾ���ɹ���");
                return SUCCESS;
            }
        }
    }

    //ѧ����ѯ
    public String selectStudent() {
        if (student.getReaderId().equals("")) {
            this.setMessage("���������֤��!");
            return SUCCESS;
        } else {
            Student stu = studentService
                    .selectByReaderId(student.getReaderId());
            if (stu == null) {
                this.setMessage("�����ڴ˽���֤�ţ�");
                return SUCCESS;
            } else {
                Map request = (Map) ActionContext.getContext().get("request");
                request.put("onestudent", stu);
                return SUCCESS;
            }
        }
    }

    //ѧ���޸�
    public String updateStudent() throws Exception {
        if (student.getReaderId().equals("")) {
            this.setMessage("���������֤��!");
            return SUCCESS;
        } else {
            Student stu = studentService
                    .selectByReaderId(student.getReaderId());
            if (stu == null) {
                this.setMessage("�����ڴ˽���֤�ţ�");
                return SUCCESS;
            } else {
                stu.setReaderId(student.getReaderId());
                if (!student.getPassword().equals("********")) {
                    stu.setPassword(SHA.encrypt(student.getPassword()));
                }
                stu.setName(student.getName());
                stu.setBorn(student.getBorn());
                stu.setSex(student.isSex());
                stu.setSpec(student.getSpec());
                stu.setNum(student.getNum());
                if (this.getPhoto() != null) {
                    FileInputStream fis = new FileInputStream(this.getPhoto());
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    stu.setPhoto(buffer);
                }
                studentService.updateStudent(stu);
                this.setMessage("�޸ĳɹ���");
                Map request = (Map) ActionContext.getContext().get("request");
                request.put("onestudent", stu);
                return SUCCESS;
            }
        }
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public File getPhoto() {
        return photo;
    }
    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

}
