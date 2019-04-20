package ru.hackaton.therapistcall.services;

import ru.hackaton.therapistcall.dtos.PolyclinicRequestDto;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.exception.NotFoundException;

import java.util.List;

public interface PolyclinicService {

    List<Polyclinic> getPolyclinicsByCoordinates(PolyclinicRequestDto polyclinicRequestDto);

    List<Polyclinic> getPolyclinicsByAddress(PolyclinicRequestDto polyclinicRequestDto) throws NotFoundException;
}