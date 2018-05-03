package main.java.per.agreysky.ssh.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import main.java.per.agreysky.ssh.entity.Person;
import main.java.per.agreysky.ssh.service.PersonService;
@Controller
@RequestMapping(value = "/person")
@SessionAttributes(value = "username")
public class PersonController {
    @Autowired
    private PersonService personService;
    @RequestMapping("/doLogin")
    public String doLogin(String username, String password,
            Map<String, Object> map) {
        if (Objects.equals(username, "admin")
                && Objects.equals(password, "admin")) {
            map.put("username", username);//�����request��������
            return "frame";
        }
        return "error";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("persons", this.personService.getPersons());
        return "main";
    }

    @RequestMapping(value = "/addPrompt")
    public String addPrompt() {
        return "addPerson";
    }
    @RequestMapping(value = "/updatePrompt")
    public String updatePrompt(Model model, String id) {
        model.addAttribute("person", this.personService.getPersonById(id));
        return "updatePerson";
    }
    @RequestMapping(value = "/deletePrompt")
    public String deletePrompt() {
        return "deletePerson";
    }

    @RequestMapping(value = "/getPersons")
    @ResponseBody
    public List<Person> getPersons() {//��ʾ��Ա��Ϣ
        return personService.getPersons();
    }
    @RequestMapping(value = "/getPersonById")
    @ResponseBody
    public Person getPersonById(String id) {//������Ա��Ϣ
        return personService.getPersonById(id);
    }
    @RequestMapping(value = "/addPerson")
    public String addPerson(Person person) {//�����Ա��Ϣ
        personService.addPerson(person);
        return "redirect:main";
    }
    @RequestMapping(value = "/updatePerson")
    public String updatePerson(Person person) {//�޸���Ա��Ϣ
        personService.updatePerson(person);
        return "redirect:main";
    }
    @RequestMapping(value = "/deletePerson")
    public String deletePerson(String id) {//ɾ����Ա��Ϣ
        personService.deletePerson(id);
        return "redirect:main";
    }

}
