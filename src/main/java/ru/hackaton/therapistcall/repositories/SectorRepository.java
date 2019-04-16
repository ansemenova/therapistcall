package ru.hackaton.therapistcall.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.therapistcall.entities.Address;
import ru.hackaton.therapistcall.entities.Sector;

public interface SectorRepository extends JpaRepository<Sector, Long> {

    Sector findByAddressesEquals(Address address);
}