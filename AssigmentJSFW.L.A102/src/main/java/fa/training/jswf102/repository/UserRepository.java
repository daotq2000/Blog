package fa.training.jswf102.repository;

import fa.training.jswf102.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "Select * from Users where username =:username",nativeQuery = true)
    public User checkExistUsername(@Param("username") String username);

    public Optional<User> getByEmail(String email);
}
