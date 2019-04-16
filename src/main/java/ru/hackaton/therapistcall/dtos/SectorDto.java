package ru.hackaton.therapistcall.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SectorDto {

    private Long doctorId;

    private String lastName;

    private String firstName;

    private String secondName;

    private Long sectorId;

    private Integer sectorNumber;

    private Long polyclinicId;

    private String polyclinicName;

    private String address;
}
