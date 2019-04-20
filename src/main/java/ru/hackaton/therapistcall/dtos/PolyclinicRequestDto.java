package ru.hackaton.therapistcall.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.hackaton.therapistcall.enums.Examination;
import ru.hackaton.therapistcall.enums.Speciality;

@Data
@NoArgsConstructor
public class PolyclinicRequestDto {

    private String address;

    private CoordinatesDto ownCoordinates;

    private Speciality doctorSpeciality;

    private Examination examinationType;

    private Boolean equipped;
}