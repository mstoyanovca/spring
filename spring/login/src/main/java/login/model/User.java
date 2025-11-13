package login.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
public record User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)  @Column(columnDefinition = "bigint") Long id,
                   @Email(message = "Email is invalid") @Column(columnDefinition = "varchar(64) not null") String email,
                   @Size(min = 6, message = "Password min length is 6 characters") @Column(columnDefinition = "varchar(32) not null") String password,
                   @Enumerated(EnumType.STRING)  @Column(columnDefinition = "varchar(32) not null default 'DISABLED'") Authority authority) {

}
