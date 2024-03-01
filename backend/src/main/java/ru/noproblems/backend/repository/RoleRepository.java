package ru.noproblems.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.noproblems.backend.data.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
