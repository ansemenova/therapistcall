package ru.hackaton.therapistcall.services;

import ru.hackaton.therapistcall.dtos.PolyclinicRequestDto;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.exception.NotFoundException;

import java.util.Set;

public interface PolyclinicService {

    Set<Polyclinic> getPolyclinicsByCoordinates(PolyclinicRequestDto polyclinicRequestDto);

    Set<Polyclinic> getPolyclinicsByAddress(PolyclinicRequestDto polyclinicRequestDto) throws NotFoundException;
}