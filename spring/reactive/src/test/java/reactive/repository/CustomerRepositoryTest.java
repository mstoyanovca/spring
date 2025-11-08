package reactive.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactive.entity.Customer;
import reactor.test.StepVerifier;

import java.util.Arrays;

@DataR2dbcTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll().block();
    }

    @Test
    public void saveTest() {
        Customer customer = new Customer(null, "firstName", "lastName");

        repository
                .save(customer)
                .flatMap(c -> repository.findById(c.id()))
                .as(StepVerifier::create)
                .expectNextMatches(c -> c.id() != null && c.firstName().equals("firstName") && c.lastName().equals("lastName"))
                .verifyComplete();
    }

    @Test
    public void saveAllTest() {
        Customer customer1 = new Customer(null, "firstName1", "lastName1");
        Customer customer2 = new Customer(null, "firstName2", "lastName2");

        repository
                .saveAll(Arrays.asList(customer1, customer2))
                .thenMany(repository.findAll())
                .as(StepVerifier::create)
                .expectNextMatches(c -> c.id() != null && c.firstName().equals("firstName1") && c.lastName().equals("lastName1"))
                .expectNextMatches(c -> c.id() != null && c.firstName().equals("firstName2") && c.lastName().equals("lastName2"))
                .verifyComplete();
    }

    @Test
    public void findByIdTest() {
        Customer customer = new Customer(null, "firstName", "lastName");

        repository
                .save(customer)
                .flatMap(c -> repository.findById(c.id()))
                .as(StepVerifier::create)
                .expectNextMatches(c -> c.id() != null && c.firstName().equals("firstName") && c.lastName().equals("lastName"))
                .verifyComplete();
    }

    @Test
    public void findAllTest() {
        Customer customer1 = new Customer(null, "firstName1", "lastName1");
        Customer customer2 = new Customer(null, "firstName2", "lastName2");

        repository
                .saveAll(Arrays.asList(customer1, customer2))
                .thenMany(repository.findAll())
                .as(StepVerifier::create)
                .expectNextMatches(c -> c.id() != null && c.firstName().equals("firstName1") && c.lastName().equals("lastName1"))
                .expectNextMatches(c -> c.id() != null && c.firstName().equals("firstName2") && c.lastName().equals("lastName2"))
                .verifyComplete();
    }

    @Test
    public void deleteByIdTest() {
        Customer customer = new Customer(null, "firstName", "lastName");

        repository
                .save(customer)
                .flatMap(c -> repository.deleteById(c.id()))
                .thenMany(repository.findAll())
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void deleteTest() {
        Customer customer = new Customer(null, "firstName", "lastName");

        repository
                .save(customer)
                .flatMap(c -> repository.delete(c))
                .thenMany(repository.findAll())
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void deleteAllTest() {
        Customer customer = new Customer(null, "firstName", "lastName");

        repository
                .save(customer)
                .then(repository.deleteAll())
                .thenMany(repository.findAll())
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }
}
