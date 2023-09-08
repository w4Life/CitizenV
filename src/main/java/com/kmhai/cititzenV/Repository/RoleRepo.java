package com.kmhai.cititzenV.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kmhai.cititzenV.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Boolean existsByRole(String role);

    Role findByRole(String role);
}
