package ru.hackaton.therapistcall.repositories;

import com.vividsolutions.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.enums.Examination;
import ru.hackaton.therapistcall.enums.Speciality;

import java.util.List;
import java.util.stream.Stream;

public interface PolyclinicRepository extends JpaRepository<Polyclinic, Long> {

    @Query(value = "select * from polyclinic p  ORDER BY ST_Distance(ST_Point(?1, ?2), p.coordinate) ASC", nativeQuery = true)
    Stream<Polyclinic> findNearest(@Param("longitude")Double longitude, @Param("latitude")Double latitude);
}