package com.subskill.repository;

import com.subskill.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileInterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findById(Long id);
    List<Interest> findByNameContaining(String name);


}
