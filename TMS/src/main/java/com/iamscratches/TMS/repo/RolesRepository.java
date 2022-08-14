package com.iamscratches.TMS.repo;

import com.iamscratches.TMS.model.Roles;
import com.iamscratches.TMS.model.User;
import com.iamscratches.TMS.model.roles.RolesPkId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Roles, RolesPkId> {
    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    List<Roles> findByUsername(String username);

    boolean existsByUsernameAndRolename(String username, String rolename);

    void deleteByUsernameAndRolename(String username, String rolename);
}
