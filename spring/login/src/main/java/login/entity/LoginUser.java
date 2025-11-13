package login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
// user is a preserved word in H2 and other DBs
public record LoginUser(@Id Long id,
                        @Email(message = "Email is invalid") String email,
                        @Size(min = 6, message = "Password min length is 6 characters") String password,
                        @NotEmpty(message = "Authority can not be empty") @Enumerated(EnumType.STRING) Authority authority) {

}
