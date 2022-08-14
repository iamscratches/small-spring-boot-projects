package com.iamscratches.TMS.repo;

import com.iamscratches.TMS.model.Registration;
import com.iamscratches.TMS.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    boolean existsByVehicleNo(Integer vehicleNo);

    boolean existsByVehicleIdAndOwnerId(Integer vehicleId, Integer ownerId);

    boolean existsByVehicleId(Integer vehicleId);
}
