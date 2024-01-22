package subskill.subskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import subskill.subskill.models.User;


import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
