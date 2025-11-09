package reactive.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactive.entity.Customer;
import reactive.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository repository;
    @InjectMocks
    private CustomerService service;

    private static final int QUANTITY = 1000;

    @Test
    public void createCustomersTest() {
        List<Customer> customers = IntStream
                .rangeClosed(1, QUANTITY)
                .boxed()
                .map(i -> new Customer(null, "firstname" + i, "lastname" + i))
                .toList();

        when(repository.deleteAll()).thenReturn(Mono.empty());
        when(repository.saveAll(customers)).thenReturn(Flux.fromIterable(customers));
        when(repository.findAll()).thenReturn(Flux.fromIterable(customers));

        StepVerifier
                .create(service.createCustomers())
                .expectNextCount(1000)
                .verifyComplete();

        verify(repository, times(1)).deleteAll();
        verify(repository, times(1)).saveAll(customers);
        verify(repository, times(1)).findAll();
    }
}
