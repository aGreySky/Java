package bean;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
public class Employee {
    private int id;
    private String name;
    private int age;
    private Properties baseProperties;
    String[] friendName; //数组  
    List<String> schoolList; //list集合  
    Set<String> partSet; //set集合  
    Map<String, String> parentMap; //map集合

    public String[] getFriendName() {
        return friendName;
    }
    public void setFriendName(String[] friendName) {
        this.friendName = friendName;
    }
    public List<String> getSchoolList() {
        return schoolList;
    }
    public void setSchoolList(List<String> schoolList) {
        this.schoolList = schoolList;
    }
    public Set<String> getPartSet() {
        return partSet;
    }
    public void setPartSet(Set<String> partSet) {
        this.partSet = partSet;
    }
    public Map<String, String> getParentMap() {
        return parentMap;
    }
    public void setParentMap(Map<String, String> parentMap) {
        this.parentMap = parentMap;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Properties getBaseProperties() {
        return baseProperties;
    }
    public void setBaseProperties(Properties baseProperties) {
        this.baseProperties = baseProperties;
    }

}
