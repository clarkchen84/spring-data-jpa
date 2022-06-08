package sizhe.chen.querydsl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sizhe.chen.querydsl.model.Customer;
import sizhe.chen.querydsl.repository.CustomerQuerydslRepository;

import java.util.List;

@Repository
public class CustomerDao {

    @Autowired
    private CustomerQuerydslRepository repository;

    public List<Customer> findAll(){
        return (List<Customer>) repository.findAll();
    }
}
