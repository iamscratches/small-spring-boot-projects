package com.iamscratches.TMS.repo;

import com.iamscratches.TMS.model.OffenceDetails;
import com.iamscratches.TMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffenceDetailsRepository extends JpaRepository<OffenceDetails, Integer> {
}
