package per.agreysky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import per.agreysky.properties.GirlProperties;

@RestController
public class HelloController {

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = {"/hello/{id}"},method = RequestMethod.GET)
    public String say(@PathVariable("id") Integer id){
        return "id="+id;
//        return girlProperties.getCupSize();
    }
}
