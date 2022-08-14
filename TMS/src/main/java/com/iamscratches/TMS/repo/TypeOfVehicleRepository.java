package com.iamscratches.TMS.repo;

import com.iamscratches.TMS.model.TypeOfVehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfVehicleRepository extends JpaRepository<TypeOfVehicles, Integer> {
    boolean existsByVehicleCategory(String vehicleCategory);
    TypeOfVehicles findByVehicleCategory(String vehicleCategory);
    void deleteByVehicleCategory(String vehicleCategory);
}
