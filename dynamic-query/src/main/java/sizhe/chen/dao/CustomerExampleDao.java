package sizhe.chen.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sizhe.chen.model.Customer;
import sizhe.chen.repository.CustomerRepository;

import java.util.List;

@Repository
public class CustomerExampleDao {

    @Autowired
    private CustomerRepository repository;

    public List<Customer> findByNameAndAddress(String name,String address){
        Customer customer = new Customer();
        customer.setCustName(name);
        customer.setCustAddress(address);
        Example example = Example.of(customer);
        List<Customer> customers = (List<Customer>) repository.findAll(example);
        return  customers;
    }
    public List<Customer> findByNameAndAddressMarcher(String name,String address){
        ExampleMatcher matcher = ExampleMatcher.matching()//.withIgnoreCase("custName")
               //.withIgnoreCase("custAddress")
                //.withStringMatcher(ExampleMatcher.StringMatcher.ENDING);
                .withMatcher("custName",t -> t.endsWith());
        Customer customer = new Customer();
        customer.setCustName(name);
        customer.setCustAddress(address);
        Example example = Example.of(customer,matcher);
        List<Customer> customers = (List<Customer>) repository.findAll(example);
        return  customers;
    }

    @Transactional
    public Customer save(String name,String address){
        Customer customer = new Customer();
        customer.setCustName(name);
        customer.setCustAddress(address);
       // Example example = Example.of(customer);
       // List<Customer> customers = (List<Customer>) repository.findAll(example);
        return repository.save(customer);
    }

}
