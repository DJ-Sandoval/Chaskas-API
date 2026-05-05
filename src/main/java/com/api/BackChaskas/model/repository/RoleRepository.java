package com.api.BackChaskas.model.repository;

import com.api.BackChaskas.model.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
