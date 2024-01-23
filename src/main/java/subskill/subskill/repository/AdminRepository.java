package subskill.subskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import subskill.subskill.models.Admins;


import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admins,Long> {
    Optional<Admins> findById(Long id);
    Optional<Admins> findByArticleName(String articleName);
}
