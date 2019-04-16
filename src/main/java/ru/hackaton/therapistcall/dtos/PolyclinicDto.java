package ru.hackaton.therapistcall.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PolyclinicDto {
    private Long id;

    private String name;

    private String address;
}
