package org.vin.edgecloudsim.mobility;

import org.vin.edgecloudsim.utility.Location;

public abstract class MobilityModel {
    protected int numberOfSensors;
    protected double simulationTime;

    public MobilityModel(int _numberOfSensors, double _simulationTime) {
        numberOfSensors = _numberOfSensors;
        simulationTime = _simulationTime;
    }

    public MobilityModel() {
    }

    public abstract void initialize();

    public abstract Location getLocation(int deviceId, double time);
}
