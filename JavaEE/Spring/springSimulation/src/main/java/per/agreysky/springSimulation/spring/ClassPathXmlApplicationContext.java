package per.agreysky.springSimulation.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPath;

import per.agreysky.springSimulation.bean.Employee;

public class ClassPathXmlApplicationContext implements BeanFactory {

    //容器的核心，用来存放注入的Bean  
    private Map<String, Object> container = new HashMap<String, Object>();

    //解析xml文件，通过反射将配置的bean放到container中  
    public ClassPathXmlApplicationContext(String fileName) throws Exception {
        SAXBuilder sb = new SAXBuilder();
        Document doc = sb.build(
                this.getClass().getClassLoader().getResourceAsStream(fileName));
        Element root = doc.getRootElement();
        List list = XPath.selectNodes(root, "/beans/bean");

        //扫描配置文件中的bean  
        for (int i = 0; i < list.size(); i++) {
            Element bean = (Element) list.get(i);
            String id = bean.getAttributeValue("id");
            String clazz = bean.getAttributeValue("class");
            Employee e = (Employee) Class.forName(clazz).newInstance();
            e.setId(i + 1);
            e.setName("zzq" + i);
            e.setAge(20 + i);
            container.put(id, e);
        }
    }

    public Object getBean(String id) {
        return container.get(id);
    }

}
