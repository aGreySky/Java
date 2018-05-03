package per.agreysky.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import per.agreysky.Exception.GirlException;
import per.agreysky.domain.Result;
import per.agreysky.enums.ResultEnum;
import per.agreysky.repository.GirlRepository;
import per.agreysky.domain.Girl;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo(){
        Girl girlA=new Girl();
        girlA.setCupSize("A");
        girlA.setAge(12);
        girlRepository.save(girlA);

        Girl girlB=new Girl();
        girlB.setCupSize("B");
        girlB.setAge(12);
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception{
        Girl girl=girlRepository.findOne(id);
        Integer age=girl.getAge();
        if(age<10){
            //返回你还在上小学
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age>10 && age<16){
            //返回你可能在上初中
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
    }

    /**
     * 通过id查找一个女生的信息
     * @param id
     * @return
     */
    public Girl findOne(Integer id){
        return girlRepository.findOne(id);
    }
}
