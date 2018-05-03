
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.Employee;

public class SpringTest {
    @Test
    public void SpringTest() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        Employee emp = (Employee) ac.getBean("emp");
        System.out.println("========基本属性注入============");
        System.out.println("id:" + emp.getId() + "\n姓名:" + emp.getName()
                + "\n年龄:" + emp.getAge());
        System.out.println("========Set集合类型注入============");
        for (String str : emp.getPartSet()) {
            System.out.println(str);
        }
        System.out.println("========list集合类型注入============");
        for (String i : emp.getSchoolList()) {
            System.out.println(i);
        }
        System.out.println("========properties集合类型注入============");
        for (Object key : emp.getBaseProperties().keySet()) {
            System.out.println(key + "="
                    + emp.getBaseProperties().getProperty(key.toString()));
        }
        System.out.println("========map集合类型注入============");
        for (Object key : emp.getParentMap().keySet()) {
            System.out.println(key + "=" + emp.getParentMap().get(key));
        }
    }
}
