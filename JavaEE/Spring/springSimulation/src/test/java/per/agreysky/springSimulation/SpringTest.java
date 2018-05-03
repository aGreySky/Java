package per.agreysky.springSimulation;

import junit.framework.TestCase;
import per.agreysky.springSimulation.bean.Employee;
import per.agreysky.springSimulation.spring.BeanFactory;
import per.agreysky.springSimulation.spring.ClassPathXmlApplicationContext;

public class SpringTest extends TestCase {

    public void test() throws Exception {
        BeanFactory factory = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        for (int i = 0; i < 3; i++) {
            Employee e = (Employee) factory.getBean("emp" + i);
            System.out.println("id:" + e.getId() + "\n姓名:" + e.getName()
                    + "\n年龄:" + e.getAge());
        }
    }

}
