package reactive.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactive.entity.Customer;

public interface CustomerRepository extends R2dbcRepository<Customer, Long> {
}
