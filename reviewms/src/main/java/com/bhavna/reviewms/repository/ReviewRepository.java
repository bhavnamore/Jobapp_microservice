package com.bhavna.reviewms.repository;
import com.bhavna.reviewms.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer>
{
    Optional<Review> findByIdAndCompanyId(int id, Long companyId);
    Optional<Review> deleteByIdAndCompanyId(int id,Long companyId);
}
