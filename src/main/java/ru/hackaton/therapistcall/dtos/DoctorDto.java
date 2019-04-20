package ru.hackaton.therapistcall.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String secondName;
}
