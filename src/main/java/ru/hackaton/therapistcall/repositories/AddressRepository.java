package ru.hackaton.therapistcall.repositories;

import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackaton.therapistcall.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findAddressByStreetAndHouse(final String street, final String house);

    Address findAddressByPoint(final Point point);
}
