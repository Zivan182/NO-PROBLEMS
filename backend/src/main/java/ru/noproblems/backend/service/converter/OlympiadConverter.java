package ru.noproblems.backend.service.converter;

import org.springframework.stereotype.Component;

import ru.noproblems.backend.data.Olympiad;
import ru.noproblems.backend.service.dto.OlympiadDto;

@Component
public class OlympiadConverter {
    public OlympiadDto toDto(Olympiad olympiad) {
        if (olympiad == null) {
            return null;
        }
        OlympiadDto olympiadDto = new OlympiadDto();
        olympiadDto.setId(olympiad.getId());
        olympiadDto.setName(olympiad.getName());
        return olympiadDto;
    }

    public Olympiad toEntity(OlympiadDto olympiadDto) {
        if (olympiadDto == null) {
            return null;
        }
        Olympiad olympiad = new Olympiad();
        olympiad.setId(olympiadDto.getId());
        olympiad.setName(olympiadDto.getName());
        return olympiad;
    }
}