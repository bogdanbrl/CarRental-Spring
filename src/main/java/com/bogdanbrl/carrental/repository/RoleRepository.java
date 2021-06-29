package com.bogdanbrl.carrental.repository;

import com.bogdanbrl.carrental.models.ERole;
import com.bogdanbrl.carrental.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
