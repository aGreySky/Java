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
    public List<Person> getPersons() {//��ʾ��Ա��Ϣ
        return personDAO.getPersons();

    }
    public Person getPersonById(String id) {//������Ա��Ϣ
        return personDAO.getPersonById(id);
    }
    public void addPerson(Person person) {//�����Ա��Ϣ
        personDAO.addPerson(person);
    }
    public void updatePerson(Person person) {//�޸���Ա��Ϣ
        personDAO.updatePerson(person);
    }
    public void deletePerson(String id) {//ɾ����Ա��Ϣ
        personDAO.deletePerson(id);
    }

}
