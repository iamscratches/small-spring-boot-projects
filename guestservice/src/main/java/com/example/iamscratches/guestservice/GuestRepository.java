package com.example.iamscratches.guestservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Frank P. Moley III.
 */
@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
