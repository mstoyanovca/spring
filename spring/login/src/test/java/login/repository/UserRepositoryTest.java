package login.repository;

import login.model.Authority;
import login.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    /*@BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }*/

    @Test
    void findByEmailAndPasswordTest() {
        User user = new User(null, "a@a.com", "password", Authority.DISABLED);
        repository.save(user);
        assertThat(user.id()).isNotNull();
        assertThat(repository.findByEmailAndPassword("a@a.com", "password")).isNotNull();
    }
}
