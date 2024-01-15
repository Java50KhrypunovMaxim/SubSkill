package subskill.subskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import subskill.subskill.models.Users;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findById(Long id);
    Optional<Users> findByEmail(String email);
}
