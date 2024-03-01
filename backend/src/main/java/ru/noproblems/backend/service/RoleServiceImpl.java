package ru.noproblems.backend.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.noproblems.backend.data.Role;
import ru.noproblems.backend.repository.RoleRepository;
import ru.noproblems.backend.service.converter.RoleConverter;
import ru.noproblems.backend.service.dto.RoleDto;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Override
    public RoleDto getRoleByName(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            return null;
        }
        return roleConverter.toDto(role);
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleConverter.toEntity(roleDto);
        Role newRole = roleRepository.save(role);
        return roleConverter.toDto(newRole);

    }
    
}
