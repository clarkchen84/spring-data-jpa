package sizhe.chen.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import sizhe.chen.querydsl.config.QuerydslConfig;
import sizhe.chen.querydsl.dao.CustomerDao;

@SpringJUnitConfig(QuerydslConfig.class)
@RunWith(SpringRunner.class)
public class QueryDslTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindAll(){
        System.out.println(customerDao.findAll());
    }

}
