package ru.hackaton.therapistcall.services;

import ru.hackaton.therapistcall.entities.Sector;

public interface PolyclinicService {

    Sector getPolyclinicByAddress(Double x, Double y);

    Sector getPolyclinicByAddress(String street, String house);
}
