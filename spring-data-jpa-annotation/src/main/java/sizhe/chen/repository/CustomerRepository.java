package sizhe.chen.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sizhe.chen.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
