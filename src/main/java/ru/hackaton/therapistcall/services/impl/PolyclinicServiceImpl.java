package ru.hackaton.therapistcall.services.impl;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hackaton.therapistcall.TherapistCallApplication;
import ru.hackaton.therapistcall.dtos.CoordinateDto;
import ru.hackaton.therapistcall.dtos.CoordinatesDto;
import ru.hackaton.therapistcall.dtos.PolyclinicRequestDto;
import ru.hackaton.therapistcall.dtos.Position;
import ru.hackaton.therapistcall.entities.Doctor;
import ru.hackaton.therapistcall.entities.ExaminationAvailability;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.exception.NotFoundException;
import ru.hackaton.therapistcall.repositories.PolyclinicRepository;
import ru.hackaton.therapistcall.services.PolyclinicService;
import ru.hackaton.therapistcall.yandex_integration.YandexGeocodeApi;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PolyclinicServiceImpl implements PolyclinicService {

    private final PolyclinicRepository polyclinicRepository;

    private final YandexGeocodeApi yandexGeocodeApi;

    private final TherapistCallApplication.YandexKeyHolder yandexKeyHolder;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Override
    @Transactional(readOnly = true)
    public Set<Polyclinic> getPolyclinicsByCoordinates(PolyclinicRequestDto polyclinicRequestDto) {
        CoordinatesDto ownCoordinates = polyclinicRequestDto.getOwnCoordinates();
        Point pointByCoordinates = geometryFactory.createPoint(new Coordinate(ownCoordinates.getLongitude(), ownCoordinates.getLatitude()));
        return getPolyclinics(polyclinicRequestDto, pointByCoordinates);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Polyclinic> getPolyclinicsByAddress(PolyclinicRequestDto polyclinicRequestDto) throws NotFoundException {
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


    private Set<Polyclinic> getPolyclinics(PolyclinicRequestDto polyclinicRequestDto, Point pointByCoordinates) {
        if(polyclinicRequestDto.getDoctorSpeciality() != null) {
            if(polyclinicRequestDto.getEquipped()) {
                return polyclinicRepository.findNearest(pointByCoordinates.getX(), pointByCoordinates.getY())
                        .flatMap(polyclinic -> polyclinic.getAvailabilities().stream())
                        .filter(examinationAvailability ->
                                examinationAvailability.getEquipped()
                                        && examinationAvailability.getExamination() == polyclinicRequestDto.getExaminationType())
                        .map(ExaminationAvailability::getPolyclinic).collect(Collectors.toSet());
            }
            else {
                return polyclinicRepository.findNearest(pointByCoordinates.getX(), pointByCoordinates.getY())
                        .flatMap(polyclinic -> polyclinic.getAvailabilities().stream())
                        .filter(examinationAvailability ->
                                examinationAvailability.getExamination() == polyclinicRequestDto.getExaminationType())
                        .map(ExaminationAvailability::getPolyclinic).collect(Collectors.toSet());
            }
        }
        else {
            if(polyclinicRequestDto.getEquipped()) {
                return polyclinicRepository.findNearest(pointByCoordinates.getX(), pointByCoordinates.getY())
                        .flatMap(polyclinic -> polyclinic.getDoctors().stream())
                        .filter(doctor ->
                                doctor.getEquipped()
                                        && doctor.getSpeciality() == polyclinicRequestDto.getDoctorSpeciality())
                        .map(Doctor::getPolyclinic).collect(Collectors.toSet());
            }
            else {
                return polyclinicRepository.findNearest(pointByCoordinates.getX(), pointByCoordinates.getY())
                        .flatMap(polyclinic -> polyclinic.getDoctors().stream())
                        .filter(doctor ->
                                doctor.getSpeciality() == polyclinicRequestDto.getDoctorSpeciality())
                        .map(Doctor::getPolyclinic).collect(Collectors.toSet());
            }
        }
    }
}