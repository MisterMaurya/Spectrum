package com.spectrum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spectrum.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

  public Role findByRoleName(String roleName);

}