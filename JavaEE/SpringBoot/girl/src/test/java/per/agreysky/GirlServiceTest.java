package per.agreysky;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.agreysky.domain.Girl;
import per.agreysky.service.GirlService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GirlServiceTest {
    @Autowired
    private GirlService girlService;

    @Test
    public  void findOneTest(){
        Girl girl=girlService.findOne(73);
        Assert.assertEquals(new Integer(14),girl.getAge());
    }
}
