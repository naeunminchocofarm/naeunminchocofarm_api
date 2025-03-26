package com.naeunminchocofarm.ncf_api.humidity.mapper;

import com.naeunminchocofarm.ncf_api.humidity.entity.Humidity;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryHumidityMapper implements HumidityMapper {
    private static Integer latstIndex = 0;
    private static List<Humidity> table = new ArrayList<>();

    @Override
    public List<Humidity> getAll() {
        return table;
    }

    @Override
    public void insert(Float value, OffsetDateTime measuredAt) {
        latstIndex++;
        var data = new Humidity(latstIndex, value, measuredAt);
        table.add(data);
    }
}
