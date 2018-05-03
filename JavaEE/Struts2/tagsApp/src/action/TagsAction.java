package action;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import vo.Person;

public class TagsAction extends ActionSupport {

    private Person per;

    @Override
    public String execute() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int thisYear = cal.get(Calendar.YEAR);
        cal.setTime(per.getBirthday());
        int birthYear = cal.get(Calendar.YEAR);
        int age = thisYear - birthYear;
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("age", age);
        return SUCCESS;
    }

    public Person getPer() {
        return per;
    }

    public void setPer(Person per) {
        this.per = per;
    }
}
