package per.agreysky.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import per.agreysky.aspect.HttpAspect;
import per.agreysky.domain.Girl;
import per.agreysky.domain.Result;
import per.agreysky.repository.GirlRepository;
import per.agreysky.service.GirlService;
import per.agreysky.util.ResultUtil;

import javax.validation.Valid;
import java.util.List;
@RestController
public class GirlController {
    private final static Logger logger = LoggerFactory.getLogger(GirlController.class);

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询所有女生
     * @return
     */
    @GetMapping("/girls")
    public List<Girl> girlList(){
        logger.info("girlList");
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     * @return
     */
    @PostMapping("/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult){
        Result result=new Result();
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlRepository.save(girl));
    }
    /**
     * 查询一个女生
     * @return
     */
    @GetMapping("/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }
    /**
     * 更新一个女生
     * @return
     */
    @PutMapping("/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,@RequestParam("cupSize") String cupSize,
                           @RequestParam("age") Integer age){
        Girl girl=new Girl();
        girl.setId(id);
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

    /**
     * 删除一个女生
     */
    @DeleteMapping("/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

    //通过年龄查询
    @GetMapping("/girls/age/{age}")
    public List<Girl> girlListByAage(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }

    @PostMapping("/girls/two")
    public void girlTwo(){
        girlService.insertTwo();
    }

    @GetMapping("/girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id)throws Exception{
        girlService.getAge(id);
    }
}
