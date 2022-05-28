package sizhe.chen.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import sizhe.chen.config.JpaConfig;
import sizhe.chen.dao.CustomerExampleDao;
import sizhe.chen.model.Customer;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = JpaConfig.class)
public class JpaTest {
    @Autowired
    private CustomerExampleDao dao;
    @Test
    public void testExample(){
        System.out.println(dao.findByNameAndAddress("张三", null));
        System.out.println("==============================");
        System.out.println(dao.findByNameAndAddressMarcher("san", "dalian"));

    }

    @Test
    public void testC(){

        dao.save("zhangsan","dalian");
        dao.save("ZHANGSAN","dalian");
    }
}
