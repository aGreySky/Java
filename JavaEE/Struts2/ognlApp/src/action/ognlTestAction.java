package action;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import vo.student;

public class ognlTestAction extends ActionSupport {

    private String[] stuNums = {"0901", "0902", "0903", "0904", "0905", "0906"};
    private String[] stuNames = {"�ų�", "��ΰ", "����", "�Ա�", "лõ", "����"};
    private boolean[] sexs = {true, true, false, false, false, true};
    private int[] ages = {21, 20, 18, 19, 21, 19};
    private String[] addresses = {"����", "�Ĵ�", "�㶫", "�Ĵ�", "����", "�Ĵ�"};

    @Override
    public String execute() {
        ArrayList<student> studentList = new ArrayList<student>();
        for (int i = 0; i < stuNums.length; i++) {
            student stu = new student();
            stu.setStuNum(stuNums[i]);
            stu.setStuName(stuNames[i]);
            stu.setSex(sexs[i]);
            stu.setAge(ages[i]);
            stu.setAddress(addresses[i]);
            studentList.add(stu);
        }
        Map session = ActionContext.getContext().getSession();
        session.put("studentList", studentList);
        session.put("CLASSPATH", System.getenv("CLASSPATH"));
        return SUCCESS;
    }
}
