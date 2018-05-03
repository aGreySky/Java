package aop.aspectj.biz;

import org.springframework.stereotype.Service;

import aop.aspectj.MoocMethod;

@Service
public class MoocBiz {

    @MoocMethod("MoocBiz save with MoocMethod.")
    public String save(String arg) {
        System.out.println("MoocBiz save : " + arg);
        // throw new RuntimeException();
        return " Save success!";
    }

}
