package main.java.per.agreysky.ssh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.java.per.agreysky.ssh.dao.PersonDAO;
import main.java.per.agreysky.ssh.entity.Person;
@Transactional
@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;
    public List<Person> getPersons() {//显示人员信息
        return personDAO.getPersons();

    }
    public Person getPersonById(String id) {//查找人员信息
        return personDAO.getPersonById(id);
    }
    public void addPerson(Person person) {//添加人员信息
        personDAO.addPerson(person);
    }
    public void updatePerson(Person person) {//修改人员信息
        personDAO.updatePerson(person);
    }
    public void deletePerson(String id) {//删除人员信息
        personDAO.deletePerson(id);
    }

}
