package ru.hackaton.therapistcall.mappers;

import org.modelmapper.ModelMapper;
import ru.hackaton.therapistcall.dtos.DoctorDto;
import ru.hackaton.therapistcall.entities.Doctor;

import java.util.List;
import java.util.stream.Collectors;

public class DoctorMapper {

    private static ModelMapper mapper = new ModelMapper();

    private static DoctorDto asDto(Doctor doctor) {
        return mapper.map(doctor, DoctorDto.class);
    }

    public static List<DoctorDto> asDtos(List<Doctor> doctors) {
        return doctors.stream().map(DoctorMapper::asDto).collect(Collectors.toList());
    }
}