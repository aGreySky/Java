package ioc.injection.service;

import ioc.injection.dao.InjectionDAO;

public class InjectionServiceImpl implements InjectionService {

    private InjectionDAO injectionDAO;

    // 构造器注入
    public InjectionServiceImpl(InjectionDAO injectionDAO) {
        this.injectionDAO = injectionDAO;
    }

    @Override
    public void save(String arg) {
        // 模拟业务操作
        System.out.println("Service接收参数：" + arg);
        arg = arg + ":" + this.hashCode();
        injectionDAO.save(arg);
    }

    // 设值主入
    public void setInjectionDAO(InjectionDAO injectionDAO) {
        this.injectionDAO = injectionDAO;
    }

}
