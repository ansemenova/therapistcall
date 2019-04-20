package ru.hackaton.therapistcall;

import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.hackaton.therapistcall.dtos.Position;
import ru.hackaton.therapistcall.entities.Address;
import ru.hackaton.therapistcall.entities.Polyclinic;
import ru.hackaton.therapistcall.repositories.PolyclinicRepository;
import ru.hackaton.therapistcall.yandex_integration.YandexGeocodeApi;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TherapistCallApplication.class, webEnvironment = DEFINED_PORT)
public class FindPolyclinicTest {

    @Autowired
    private PolyclinicRepository polyclinicRepository;

    private final GeometryFactory geometryFactory = new GeometryFactory();

    @Test
    public void createPolyclinic() {
        createPolyclinicRepo("Number 5", "44.014628 56.323612");
        createPolyclinicRepo("Семашко", "44.077968 56.310143");
        createPolyclinicRepo("Помц", "44.031939 56.325424");
    }

    @Test
    @Transactional
    public void findNearestPolyclinic() {
        System.out.println(polyclinicRepository.findNearest(44.013847,56.320338));
    }

    private void createPolyclinicRepo(String name, String coordinate) {
        Point pointByCoordinates;
        Polyclinic polyclinic;
        pointByCoordinates = geometryFactory.createPoint(Position.builder().yandexPos(coordinate).build().getCoordinate());
        polyclinic = Polyclinic.builder().name(name).address(
                Address.builder().coordinate(pointByCoordinates).build()).build();
        polyclinicRepository.save(polyclinic);
    }
}