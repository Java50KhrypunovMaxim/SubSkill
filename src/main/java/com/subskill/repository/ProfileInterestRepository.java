package com.subskill.repository;

import com.subskill.enums.Tags;
import com.subskill.models.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileInterestRepository extends JpaRepository<Interest, Long> {
    Optional<Interest> findById(Long id);

    @Modifying
    @Query("DELETE FROM Interest i WHERE i.name = :tagName")
    void deleteByName(@Param("tagName") Tags tagName);
}
