package ru.hackaton.therapistcall.mappers;

import ru.hackaton.therapistcall.dtos.SectorDto;
import ru.hackaton.therapistcall.entities.Doctor;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.entities.Sector;

public class SectorMapper {

    public static SectorDto asDto(Sector sector) {
        Doctor doctor = sector.getDoctor();
        Polyclinic polyclinic = sector.getPolyclinic();
        return SectorDto.builder().doctorId(doctor.getId())
                .lastName(doctor.getLastName())
                .firstName(doctor.getFirstName())
                .secondName(doctor.getSecondName())
                .sectorId(sector.getId())
                .sectorNumber(sector.getNumber())
                .polyclinicId(polyclinic.getId())
                .polyclinicName(polyclinic.getName())
                .address(polyclinic.getAddress()).build();
    }
}