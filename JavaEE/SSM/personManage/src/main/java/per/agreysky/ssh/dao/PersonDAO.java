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
    public List<Person> getPersons() {//显示人员信息
        return this.getSession().createCriteria(Person.class).list();

    }
    public Person getPersonById(String id) {
        return (Person) this.getSession().createQuery("from Person where id=?")
                .setParameter(0, id).uniqueResult();
    }
    public void addPerson(Person person) {//添加人员信息
        this.getSession().save(person);
    }
    public void updatePerson(Person person) {//修改人员信息
        this.getSession().update(person);
    }
    public void deletePerson(String id) {//删除人员信息
        this.getSession().createQuery("delete Person where id=?")
                .setParameter(0, id).executeUpdate();
    }
}
