package sizhe.chen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sizhe.chen.model.Customer;
import sizhe.chen.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Transactional
    public Customer save(Customer customer){
       return  repository.save(customer);
    }
}
