package sizhe.chen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import sizhe.chen.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
