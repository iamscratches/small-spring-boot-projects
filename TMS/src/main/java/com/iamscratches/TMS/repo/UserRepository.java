package com.iamscratches.TMS.repo;

import com.iamscratches.TMS.model.Roles;
import com.iamscratches.TMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    User findByUsername(String username);
}
