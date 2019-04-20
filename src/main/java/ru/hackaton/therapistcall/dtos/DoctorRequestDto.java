package ru.hackaton.therapistcall.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hackaton.therapistcall.enums.Speciality;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorRequestDto {

    private Long polyclinicId;

    private Speciality speciality;

    private Boolean equipped;
}
