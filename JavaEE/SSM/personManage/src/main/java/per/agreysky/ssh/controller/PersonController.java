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
            map.put("username", username);//存放在request请求域中
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
    public List<Person> getPersons() {//显示人员信息
        return personService.getPersons();
    }
    @RequestMapping(value = "/getPersonById")
    @ResponseBody
    public Person getPersonById(String id) {//查找人员信息
        return personService.getPersonById(id);
    }
    @RequestMapping(value = "/addPerson")
    public String addPerson(Person person) {//添加人员信息
        personService.addPerson(person);
        return "redirect:main";
    }
    @RequestMapping(value = "/updatePerson")
    public String updatePerson(Person person) {//修改人员信息
        personService.updatePerson(person);
        return "redirect:main";
    }
    @RequestMapping(value = "/deletePerson")
    public String deletePerson(String id) {//删除人员信息
        personService.deletePerson(id);
        return "redirect:main";
    }

}
