package com.naeunminchocofarm.ncf_api.iot.mapper;

import com.naeunminchocofarm.ncf_api.iot.entity.AirTemperature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component("inmemoryTemperatureMapper")
public class InmemoryTemperatureMapper implements TemperatureMapper{
    private static final Logger log = LogManager.getLogger(InmemoryTemperatureMapper.class);
    private static Integer lastIndex = 0;
    private static List<AirTemperature> table = new ArrayList<>();

    @Override
    public void insert(Float temperature, OffsetDateTime measuredAt) {
        lastIndex++;
        table.add(new AirTemperature(lastIndex, temperature, measuredAt));
    }

    @Override
    public List<AirTemperature> getAll() {
        return table;
    }
}
