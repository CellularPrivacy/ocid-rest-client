package com.github.cellularprivacy.ocidrestclient.dto;

import java.util.List;

public class MeasurementList extends Count {

    private List<Measurement> measurements;

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
