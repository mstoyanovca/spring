package reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactive.entity.Customer;
import reactive.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping(path = "/create")
    public Mono<Customer> create(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping(path = "/create")
    public Mono<Customer> createFromName(@RequestParam String firstName, @RequestParam String lastName) {
        // http://localhost:8080/customer/create?firstName=Martin&lastName=Stoyanov
        Customer customer = new Customer(firstName, lastName);
        return customerRepository.save(customer);
    }

    @GetMapping(path = "/all")
    public Flux<Customer> findAll() {
        // http://localhost:8080/customer/all
        return customerRepository.findAll();
    }
}
