package ru.hackaton.therapistcall.services;

import ru.hackaton.therapistcall.entities.Doctor;
import ru.hackaton.therapistcall.enums.Speciality;

import java.util.List;

public interface DoctorService {

    List<Doctor> getDoctorBySpeciality(Speciality speciality, Boolean equipped, Long polyclinicId);
}
