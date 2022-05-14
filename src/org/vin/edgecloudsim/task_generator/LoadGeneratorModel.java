package org.vin.edgecloudsim.task_generator;

import org.vin.edgecloudsim.utility.TaskProperty;

import java.util.List;

public abstract class LoadGeneratorModel {
    protected List<TaskProperty> taskList;
    protected int noOfSensorDevices;
    protected double simulationTime;
    protected String simScenario;

    public LoadGeneratorModel(int _numberOfSensorDevices, double _simulationTime, String _simScenario) {
        noOfSensorDevices = _numberOfSensorDevices;
        simulationTime = _simulationTime;
        simScenario = _simScenario;
    }

    public LoadGeneratorModel() {
    }

    public List<TaskProperty> getTaskList() {
        return taskList;
    }

    public abstract void initializeModel();

    public abstract int getTaskTypeOfDevice(int deviceId);

}
