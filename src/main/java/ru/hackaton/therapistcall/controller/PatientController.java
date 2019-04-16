package ru.hackaton.therapistcall.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hackaton.therapistcall.dtos.SectorDto;
import ru.hackaton.therapistcall.services.PolyclinicService;

import static ru.hackaton.therapistcall.mappers.SectorMapper.asDto;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private final PolyclinicService polyclinicService;

    @GetMapping(path = "getPolyclinic")
    public SectorDto getPolyclinicByAddress(@RequestParam Double x, @RequestParam Double y) {
        return asDto(polyclinicService.getPolyclinicByAddress(x, y));
    }

    @GetMapping(path = "getPolyclinic")
    public SectorDto getPolyclinicByAddressStr(@RequestParam String street, @RequestParam String house) {
        return asDto(polyclinicService.getPolyclinicByAddress(street, house));
    }


}