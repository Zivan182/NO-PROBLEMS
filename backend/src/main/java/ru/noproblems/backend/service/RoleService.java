package ru.noproblems.backend.service;

import ru.noproblems.backend.service.dto.RoleDto;

public interface RoleService {
    RoleDto getRoleByName(String name);
    RoleDto createRole(RoleDto roleDto);
}
