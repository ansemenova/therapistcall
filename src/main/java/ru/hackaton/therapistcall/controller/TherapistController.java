package ru.hackaton.therapistcall.controller;

import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hackaton.therapistcall.dtos.PolyclinicDto;
import ru.hackaton.therapistcall.dtos.PolyclinicRequestDto;
import ru.hackaton.therapistcall.exception.NotFoundException;
import ru.hackaton.therapistcall.mappers.PolyclinicMapper;
import ru.hackaton.therapistcall.services.PolyclinicService;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
public class TherapistController {

    private final PolyclinicService polyclinicService;

    @GetMapping("getNearestPolyclinics")
    public List<PolyclinicDto> getNearestPolyclinics(@RequestBody PolyclinicRequestDto polyclinicRequestDto) throws NotFoundException {

        if(polyclinicRequestDto.getOwnCoordinates() != null) {
            return PolyclinicMapper.asDtos(polyclinicService.getPolyclinicsByCoordinates(polyclinicRequestDto));

        } else if (!StringUtils.isEmpty(polyclinicRequestDto.getAddress())){
            return PolyclinicMapper.asDtos(polyclinicService.getPolyclinicsByAddress(polyclinicRequestDto));
        }
        throw new IllegalArgumentException("Address should be filled");
    }
}
