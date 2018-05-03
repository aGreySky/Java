package main.java.per.agreysky.ssh.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import main.java.per.agreysky.ssh.entity.Person;
@Repository
public class PersonDAO {
    @Resource
    private SessionFactory sessionFactory;
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    public List<Person> getPersons() {//��ʾ��Ա��Ϣ
        return this.getSession().createCriteria(Person.class).list();

    }
    public Person getPersonById(String id) {
        return (Person) this.getSession().createQuery("from Person where id=?")
                .setParameter(0, id).uniqueResult();
    }
    public void addPerson(Person person) {//�����Ա��Ϣ
        this.getSession().save(person);
    }
    public void updatePerson(Person person) {//�޸���Ա��Ϣ
        this.getSession().update(person);
    }
    public void deletePerson(String id) {//ɾ����Ա��Ϣ
        this.getSession().createQuery("delete Person where id=?")
                .setParameter(0, id).executeUpdate();
    }
}
