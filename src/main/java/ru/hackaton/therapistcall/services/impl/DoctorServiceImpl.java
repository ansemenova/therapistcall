package ru.hackaton.therapistcall.services.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.therapistcall.entities.Doctor;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.enums.Speciality;
import ru.hackaton.therapistcall.repositories.DoctorRepository;
import ru.hackaton.therapistcall.repositories.PolyclinicRepository;
import ru.hackaton.therapistcall.services.DoctorService;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final PolyclinicRepository polyclinicRepository;

    @Override
    public List<Doctor> getDoctorBySpeciality(Speciality speciality, Boolean equipped, Long polyclinicId) {
        Polyclinic polyclinic = polyclinicRepository.findById(polyclinicId).get();
        if(equipped) {
            return doctorRepository.getByPolyclinicAndSpecialityAndEquipped(polyclinic, speciality, equipped);
        }
        return doctorRepository.getByPolyclinicAndSpeciality(polyclinic, speciality);
    }
}
