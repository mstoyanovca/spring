package reactive.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactive.entity.Customer;

@Repository
public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
}
