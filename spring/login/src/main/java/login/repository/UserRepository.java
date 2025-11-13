package login.repository;

import login.entity.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<LoginUser, Long> {
    LoginUser findByEmailAndPassword(String email, String password);
}
