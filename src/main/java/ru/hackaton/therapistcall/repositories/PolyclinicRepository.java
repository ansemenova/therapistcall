package ru.hackaton.therapistcall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.therapistcall.entities.Polyclinic;

public interface PolyclinicRepository extends JpaRepository<Polyclinic, Long> {

}
