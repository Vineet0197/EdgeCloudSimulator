package org.vin.edgecloudsim.network;

import org.vin.edgecloudsim.edge_client.Task;
import org.vin.edgecloudsim.utility.Location;

public abstract class NetworkModel {
    protected int numberOfSensors;
    protected String simScenario;

    public NetworkModel(int _numberOfSensors, String _simScenario){
        numberOfSensors = _numberOfSensors;
        simScenario = _simScenario;
    };

    /**
     * initializes custom network model
     */
    public abstract void initialize();

    /**
     * calculates the upload delay from source to destination device
     */
    public abstract double getUploadDelay(int sourceDeviceId, int destDeviceId, Task task);

    /**
     * calculates the download delay from source to destination device
     */
    public abstract double getDownloadDelay(int sourceDeviceId, int destDeviceId, Task task);

    /**
     * Mobile device manager should inform network manager about the network operation
     * This information may be important for some network delay models
     */
    public abstract void uploadStarted(Location accessPointLocation, int destDeviceId);
    public abstract void uploadFinished(Location accessPointLocation, int destDeviceId);
    public abstract void downloadStarted(Location accessPointLocation, int sourceDeviceId);
    public abstract void downloadFinished(Location accessPointLocation, int sourceDeviceId);
}
