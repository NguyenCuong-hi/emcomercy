package com.example.library.repository;

import com.example.library.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public
interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findRolesByNameRoles(String nameRoles);

}
