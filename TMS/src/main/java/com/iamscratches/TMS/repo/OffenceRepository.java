package com.iamscratches.TMS.repo;

import com.iamscratches.TMS.model.Offence;
import com.iamscratches.TMS.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OffenceRepository extends JpaRepository<Offence, Integer> {
    void deleteByOffenceId(Integer offenceId);
}
