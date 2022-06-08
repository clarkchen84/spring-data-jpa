package sizhe.chen.querydsl.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import sizhe.chen.querydsl.model.Customer;

public interface CustomerQuerydslRepository extends PagingAndSortingRepository<Customer,Long>, QuerydslPredicateExecutor<Customer> {

}
