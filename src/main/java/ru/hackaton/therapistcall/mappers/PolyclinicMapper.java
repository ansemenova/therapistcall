package ru.hackaton.therapistcall.mappers;

import org.modelmapper.ModelMapper;
import ru.hackaton.therapistcall.dtos.PolyclinicDto;
import ru.hackaton.therapistcall.entities.Polyclinic;

public class PolyclinicMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static PolyclinicDto asDto(Polyclinic entity) {
        return mapper.map(entity, PolyclinicDto.class);
    }
}
