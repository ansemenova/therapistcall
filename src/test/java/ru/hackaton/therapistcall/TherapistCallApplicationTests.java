package ru.hackaton.therapistcall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.hackaton.therapistcall.dtos.CoordinateDto;
import ru.hackaton.therapistcall.yandex_integration.YandexGeocodeApi;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TherapistCallApplication.class, webEnvironment = DEFINED_PORT)
public class TherapistCallApplicationTests {

    @Autowired
    private YandexGeocodeApi yandexGeocodeApi;

    @Autowired
    private TherapistCallApplication.YandexKeyHolder yandexKeyHolder;

    @Test
    public void yandexApiTest() {
        CoordinateDto geocodeStr = yandexGeocodeApi.getCoordinatesByGeocode("Варварская 40", yandexKeyHolder.getApiKey(), "json");
        System.out.println(geocodeStr);
    }

    @Test
    public void yandexApiAbraCadabraTest() {
        CoordinateDto geocodeStr = yandexGeocodeApi.getCoordinatesByGeocode("jhgfjhtdfajsgdaja", yandexKeyHolder.getApiKey(), "json");
        assertEquals(new Integer(0), geocodeStr.getResponse().getGeoObjectCollection().getMetaDataProperty().getGeocoderResponseMetaData().getFound());
    }
}
