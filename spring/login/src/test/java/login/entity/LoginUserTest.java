package login.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class LoginUserTest {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        if (validatorFactory != null) {
            validatorFactory.close();
        }
    }

    @Test
    public void noArgumentConstructorNeededForJpaTest() {
        assertThat(new LoginUser()).isNotNull();
    }

    @Test
    public void invalidEmailTest() {
        LoginUser loginUser = new LoginUser(null, "a", "123456", Authority.DISABLED);
        Set<ConstraintViolation<LoginUser>> violations = validator.validate(loginUser);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Email is invalid");
    }

    @Test
    public void invalidPasswordTest() {
        LoginUser loginUser = new LoginUser(null, "a@a.com", "b", Authority.DISABLED);
        Set<ConstraintViolation<LoginUser>> violations = validator.validate(loginUser);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Password min length is 6 characters");
    }

    @Test
    public void nullAuthorityTest() {
        LoginUser loginUser = new LoginUser(null, "a@a.com", "123456", null);
        Set<ConstraintViolation<LoginUser>> violations = validator.validate(loginUser);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("Authority can not be null");
    }

    @Test
    public void validUserTest() {
        LoginUser loginUser = new LoginUser(null, "a@a.com", "123456", Authority.DISABLED);
        Set<ConstraintViolation<LoginUser>> violations = validator.validate(loginUser);
        assertThat(violations).hasSize(0);
    }
}
