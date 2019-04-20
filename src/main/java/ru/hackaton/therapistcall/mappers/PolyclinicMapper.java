package ru.hackaton.therapistcall.mappers;

import org.modelmapper.ModelMapper;
import ru.hackaton.therapistcall.dtos.PolyclinicDto;
import ru.hackaton.therapistcall.entities.Polyclinic;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PolyclinicMapper {

    private static ModelMapper mapper = new ModelMapper();

    public static PolyclinicDto asDto(Polyclinic entity) {
        return mapper.map(entity, PolyclinicDto.class);
    }

    public static Set<PolyclinicDto> asDtos(Set<Polyclinic> entities) {

        return entities.stream().map(PolyclinicMapper::asDto).collect(Collectors.toSet());
    }
}
