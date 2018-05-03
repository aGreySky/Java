package ioc.interfaces;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import ioc.base.UnitTestBase;
import ioc.injection.service.InjectionService;

@RunWith(BlockJUnit4ClassRunner.class)
public class TestInjection extends UnitTestBase {

    public TestInjection() {
        super("classpath:spring-injection.xml");
    }

    @Test
    public void testSetter() {
        InjectionService service = super.getBean("injectionService");
        service.save("这是要保存的数据");
    }
}
