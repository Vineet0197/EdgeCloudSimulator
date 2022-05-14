package org.vin.edgecloudsim.utility;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.vin.edgecloudsim.core.SimSettings;

public class TaskProperty {
    private double startTime;
    private long length, inputFileSize, outputFileSize;
    private int taskType;
    private int pesNumber;
    private int sensorDeviceId;

    public TaskProperty(double _startTime, int _sensorDeviceId, int _taskType, int _pesNumber, long _length, long _inputFileSize, long _outputFileSize) {
        startTime = _startTime;
        sensorDeviceId = _sensorDeviceId;
        taskType = _taskType;
        pesNumber = _pesNumber;
        length = _length;
        inputFileSize = _inputFileSize;
        outputFileSize = _outputFileSize;
    }

    public TaskProperty(int _sensorDeviceId, int _taskType, double _startTime, ExponentialDistribution[][] expRngList) {
        sensorDeviceId = _sensorDeviceId;
        startTime = _startTime;
        taskType = _taskType;

        inputFileSize = (long)expRngList[_taskType][0].sample();
        outputFileSize = (long)expRngList[_taskType][1].sample();
        length = (long)expRngList[_taskType][2].sample();

        pesNumber = (int)SimSettings.getInstance().getTaskLookUpTable()[_taskType][8];
    }

    public TaskProperty(int sensorDeviceId, double startTime, ExponentialDistribution[] expRngList) {
        this.sensorDeviceId = sensorDeviceId;
        this.startTime = startTime;
        taskType = 0;
        inputFileSize = (long)expRngList[0].sample();
        outputFileSize = (long)expRngList[1].sample();
        length = (long) expRngList[2].sample();
        pesNumber = (int) SimSettings.getInstance().getTaskLookUpTable()[0][8];
    }

    public double getStartTime(){
        return startTime;
    }

    public long getLength(){
        return length;
    }

    public long getInputFileSize(){
        return inputFileSize;
    }

    public long getOutputFileSize(){
        return outputFileSize;
    }

    public int getTaskType(){
        return taskType;
    }

    public int getPesNumber(){
        return pesNumber;
    }

    public int getMobileDeviceId(){
        return sensorDeviceId;
    }
}
