package ru.noproblems.backend.service.converter;

import org.springframework.stereotype.Component;

import ru.noproblems.backend.data.Role;
import ru.noproblems.backend.service.dto.RoleDto;

@Component
public class RoleConverter {
    public RoleDto toDto(Role role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    public Role toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}
