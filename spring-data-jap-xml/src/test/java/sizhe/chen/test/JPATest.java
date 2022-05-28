package sizhe.chen.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sizhe.chen.model.Customer;
import sizhe.chen.repositories.CustomerRepository;
import sizhe.chen.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(locations = "classpath:/spring.xml")
public class JPATest {

    @Autowired
    private CustomerRepository repository;
    @Autowired
    private CustomerService customerService;

    @Test
    //@Transactional
    public void testC(){
        Customer customer = new Customer();
        customer.setCustAddress("大连");
        customer.setCustName("张三");
       // repository.save(customer);
        customerService.save(customer);
    }
}
