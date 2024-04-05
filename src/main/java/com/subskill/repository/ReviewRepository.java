package com.subskill.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.subskill.models.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
	 Optional<Review> findById(Long id);

}
