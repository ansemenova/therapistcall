package ru.hackaton.therapistcall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.therapistcall.entities.Doctor;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.enums.Speciality;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> getByPolyclinicAndSpecialityAndEquipped(Polyclinic polyclinic, Speciality speciality, Boolean equipped);
    List<Doctor> getByPolyclinicAndSpeciality(Polyclinic polyclinic, Speciality speciality);
}
