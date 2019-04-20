package ru.hackaton.therapistcall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.hackaton.therapistcall.entities.Doctor;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.enums.Speciality;
import ru.hackaton.therapistcall.repositories.DoctorRepository;
import ru.hackaton.therapistcall.repositories.PolyclinicRepository;
import ru.hackaton.therapistcall.services.DoctorService;

import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TherapistCallApplication.class, webEnvironment = DEFINED_PORT)
public class FindDoctorTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PolyclinicRepository polyclinicRepository;

    @Autowired
    private DoctorService doctorService;

    @Test
    public void createDoctors() {
        Polyclinic polyclinic = polyclinicRepository.findById(1L).get();
        Doctor doctor1 = Doctor.builder().firstName("Иван").lastName("Иванов").secondName("Иванович")
                .speciality(Speciality.IMMUNOLOGY).equipped(false).polyclinic(polyclinic).build();
        doctorRepository.save(doctor1);

        doctor1 = Doctor.builder().firstName("Петр").lastName("Петров").secondName("Петрович")
                .speciality(Speciality.IMMUNOLOGY).equipped(true).polyclinic(polyclinic).build();
        doctorRepository.save(doctor1);

        doctor1 = Doctor.builder().firstName("Сидр").lastName("Сидоров").secondName("Сидорович")
                .speciality(Speciality.SERGERY).equipped(false).polyclinic(polyclinic).build();
        doctorRepository.save(doctor1);
    }

    @Test
    public void findDoctorByPolyclinicAndSpeciality() {
        List<Doctor> doctorBySpeciality = doctorService.getDoctorBySpeciality(Speciality.IMMUNOLOGY,
                false, 1L);
        doctorBySpeciality.stream().forEach(d -> System.out.println(d.getLastName()));
    }
}
