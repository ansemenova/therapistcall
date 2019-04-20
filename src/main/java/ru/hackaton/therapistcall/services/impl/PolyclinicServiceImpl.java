package ru.hackaton.therapistcall.services.impl;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hackaton.therapistcall.TherapistCallApplication;
import ru.hackaton.therapistcall.dtos.CoordinateDto;
import ru.hackaton.therapistcall.dtos.CoordinatesDto;
import ru.hackaton.therapistcall.dtos.PolyclinicRequestDto;
import ru.hackaton.therapistcall.dtos.Position;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.exception.NotFoundException;
import ru.hackaton.therapistcall.repositories.PolyclinicRepository;
import ru.hackaton.therapistcall.services.PolyclinicService;
import ru.hackaton.therapistcall.yandex_integration.YandexGeocodeApi;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class PolyclinicServiceImpl implements PolyclinicService {

    private final PolyclinicRepository polyclinicRepository;

    private final YandexGeocodeApi yandexGeocodeApi;

    private final TherapistCallApplication.YandexKeyHolder yandexKeyHolder;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    public List<Polyclinic> getPolyclinicsByCoordinates(PolyclinicRequestDto polyclinicRequestDto) {
        CoordinatesDto ownCoordinates = polyclinicRequestDto.getOwnCoordinates();
        Point pointByCoordinates = geometryFactory.createPoint(new Coordinate(ownCoordinates.getLatitude(), ownCoordinates.getLongitude()));
        return getPolyclinics(polyclinicRequestDto, pointByCoordinates);
    }

    public List<Polyclinic> getPolyclinicsByAddress(PolyclinicRequestDto polyclinicRequestDto) throws NotFoundException {
        CoordinateDto coordinateDto = yandexGeocodeApi.getCoordinatesByGeocode(polyclinicRequestDto.getAddress(),
                yandexKeyHolder.getApiKey(), "json");

        if(coordinateDto.getResponse().getGeoObjectCollection().getMetaDataProperty().getGeocoderResponseMetaData().getFound() > 0) {
            Position position = new Position(coordinateDto.getResponse().getGeoObjectCollection().getFeatureMember().get(0)
                    .getGeoObject().getPoint().getPos());
            Point pointByCoordinates = geometryFactory.createPoint(position.getCoordinate());
            return getPolyclinics(polyclinicRequestDto, pointByCoordinates);
        }
        throw new NotFoundException("There is no address");
    }

    private List<Polyclinic> getPolyclinics(PolyclinicRequestDto polyclinicRequestDto, Point pointByCoordinates) {
        if(polyclinicRequestDto.getDoctorSpeciality() != null) {
//            return polyclinicRepository.getByAddress_CoordinateAndExaminationsEquals(pointByCoordinates,
//                    polyclinicRequestDto.getExaminationType());
            return  Collections.emptyList();
        }
        else {
            return polyclinicRepository.getByAddress_CoordinateAndDoctorsSpeciality(pointByCoordinates,
                    polyclinicRequestDto.getDoctorSpeciality());
        }
    }
}