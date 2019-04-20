package ru.hackaton.therapistcall.repositories;

import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.enums.Examination;
import ru.hackaton.therapistcall.enums.Speciality;

import java.util.List;

public interface PolyclinicRepository extends JpaRepository<Polyclinic, Long> {

//    List<Polyclinic> getByAddress_CoordinateAndExaminationsEquals(final Point address, final Examination examination);

    List<Polyclinic> getByAddress_CoordinateAndDoctorsSpeciality(final Point address, final Speciality speciality);

    @Query(value = "select * from polyclinic  p ORDER BY ST_Distance(ST_Point(?1, ?2), p.coordinate) ASC", nativeQuery = true)
    List<Polyclinic> findNearest(@Param("longitude")Double longitude, @Param("latitude")Double latitude);
}