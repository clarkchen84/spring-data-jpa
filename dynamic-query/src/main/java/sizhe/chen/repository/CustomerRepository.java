package sizhe.chen.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import sizhe.chen.model.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long>, QueryByExampleExecutor<Customer> {

}
