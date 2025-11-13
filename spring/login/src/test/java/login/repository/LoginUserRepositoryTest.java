package login.repository;

import login.entity.Authority;
import login.entity.LoginUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class LoginUserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }

    @Test
    void findByEmailAndPasswordTest() {
        LoginUser loginUser = new LoginUser(1L, "a@a.com", "password", Authority.DISABLED);
        repository.save(loginUser);
        assertThat(loginUser.id()).isNotNull();
        assertThat(repository.findByEmailAndPassword("a@a.com", "password")).isNotNull();
    }
}
