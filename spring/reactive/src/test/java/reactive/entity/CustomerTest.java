package reactive.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    @Test
    void constructorTest() {
        Customer customer = new Customer(1L, "firstName", "lastName");
        assertEquals("firstName", customer.firstName());
        assertEquals("lastName", customer.lastName());
        assertEquals(1L, customer.id());
    }
}
