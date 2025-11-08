package reactive.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactive.entity.Customer;
import reactive.repository.CustomerRepository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final TransactionalOperator transactionalOperator;

    private static final int QUANTITY = 1000;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    public CustomerService(CustomerRepository customerRepository, TransactionalOperator transactionalOperator) {
        this.customerRepository = customerRepository;
        this.transactionalOperator = transactionalOperator;
    }

    public Flux<Customer> createCustomers() {
        LOGGER.info("createCustomers()");

        List<Customer> customers = IntStream
                .rangeClosed(1, QUANTITY)
                .boxed()
                .map(i -> new Customer(null, "firstname" + i, "lastname" + i))
                .toList();

        return customerRepository
                .deleteAll()
                .thenMany(customerRepository.saveAll(customers))
                .thenMany(customerRepository.findAll())
                .as(transactionalOperator::transactional);
    }
}
