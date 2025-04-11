package com.naeunminchocofarm.ncf_api;

import com.naeunminchocofarm.ncf_api.smart_farm.dto.SensorDataDTO;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;

public class MyTest {
    @Test
    public void listTojsonTest(){
        var list = List.of(
                new SensorDataDTO("air_temp", 24.5, OffsetDateTime.now(), "uuid1")
                , new SensorDataDTO("humidity", 31.3, OffsetDateTime.now(), "uuid1")
                , new SensorDataDTO("sunshine", 654, OffsetDateTime.now(), "uuid1")
                , new SensorDataDTO("soil_moisture", 302, OffsetDateTime.now(), "uuid1")
        );

        System.out.println(list.toString());
    }
}
