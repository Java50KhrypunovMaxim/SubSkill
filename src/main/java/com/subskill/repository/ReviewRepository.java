package com.subskill.repository;

import com.subskill.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
	 Optional<Review> findById(Long id);

}
