package login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class LoginUser {
    @Id
    private Long id;
    @Email(message = "Email is invalid")
    private String email;
    @Size(min = 6, message = "Password min length is 6 characters")
    private String password;
    @NotNull(message = "Authority can not be null")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    // do not remove, needed for JPA
    public LoginUser() {
    }

    public LoginUser(Long id, String email, String password, Authority authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoginUser user = (LoginUser) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && getAuthority() == user.getAuthority();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getAuthority());
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email='" + email + '\'' + ", password='" + password + '\'' + ", authority=" + authority + '}';
    }
}
