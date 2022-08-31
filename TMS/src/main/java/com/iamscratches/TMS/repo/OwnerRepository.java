package com.iamscratches.TMS.repo;

import com.iamscratches.TMS.model.Owner;
import com.iamscratches.TMS.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository  extends JpaRepository<Owner, Integer> {
    boolean existsByAdhaarOrMobileNoOrPancard(String adhaar, Long mobileNo, String pancard);
    boolean existsByMobileNo(Long mobileNo);
    void deleteByMobileNo(Long mobileNo);

    boolean existsByAdhaar(String adhaar);

    void deleteByAdhaar(String adhaar);

    boolean existsByPancard(String pancard);

    void deleteByPancard(String pancard);

    Owner findByOwnerId(int id);
}
