package ru.hackaton.therapistcall.services.impl;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.therapistcall.entities.Address;
import ru.hackaton.therapistcall.entities.Sector;
import ru.hackaton.therapistcall.repositories.AddressRepository;
import ru.hackaton.therapistcall.repositories.SectorRepository;
import ru.hackaton.therapistcall.services.PolyclinicService;

@Service
@AllArgsConstructor
public class PolyclinicServiceImpl implements PolyclinicService {

    private final SectorRepository sectorRepository;

    private final AddressRepository addressRepository;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    public Sector getPolyclinicByAddress(Double x, Double y) {
        Point pointByCoordinates = geometryFactory.createPoint(new Coordinate(x, y));
        Address address = addressRepository.findAddressByPoint(pointByCoordinates);
        return sectorRepository.findByAddressesEquals(address);
    }

    @Override
    public Sector getPolyclinicByAddress(String street, String house) {
        Address address = addressRepository.findAddressByStreetAndHouse(street, house);
        return sectorRepository.findByAddressesEquals(address);
    }
}