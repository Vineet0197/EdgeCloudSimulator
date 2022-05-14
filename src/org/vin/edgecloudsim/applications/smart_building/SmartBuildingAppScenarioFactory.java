package org.vin.edgecloudsim.applications.smart_building;

import org.vin.edgecloudsim.cloud_server.CloudServerManager;
import org.vin.edgecloudsim.cloud_server.DefaultCloudServerManager;
import org.vin.edgecloudsim.core.ScenarioFactory;
import org.vin.edgecloudsim.edge_client.MobileDeviceManager;
import org.vin.edgecloudsim.edge_client.mobile_process_unit.MobileServerManager;
import org.vin.edgecloudsim.edge_orchestrator.EdgeOrchestrator;
import org.vin.edgecloudsim.edge_server.DefaultEdgeServerManager;
import org.vin.edgecloudsim.edge_server.EdgeServerManager;
import org.vin.edgecloudsim.mobility.MobilityModel;
import org.vin.edgecloudsim.mobility.NomadicMobility;
import org.vin.edgecloudsim.network.NetworkModel;
import org.vin.edgecloudsim.task_generator.IdleActiveLoadGenerator;
import org.vin.edgecloudsim.task_generator.LoadGeneratorModel;

public class SmartBuildingAppScenarioFactory implements ScenarioFactory {

    private int numOfSensors;
    private double simulationTime;
    private String orchestratorPolicy;
    private String simScenario;

    // Constructor for Scenario Factory
    SmartBuildingAppScenarioFactory(int _numOfSensors,
                          double _simulationTime,
                          String _orchestratorPolicy,
                          String _simScenario){
        orchestratorPolicy = _orchestratorPolicy;
        numOfSensors = _numOfSensors;
        simulationTime = _simulationTime;
        simScenario = _simScenario;
    }

    @Override
    public LoadGeneratorModel getLoadGeneratorModel() {
        return new IdleActiveLoadGenerator(numOfSensors, simulationTime, simScenario);
    }

    @Override
    public MobilityModel getMobilityModel() {
        return new NomadicMobility(numOfSensors, simulationTime);
    }

    @Override
    public NetworkModel getNetworkModel() {
        return new SmartBuildingAppNetworkModel(numOfSensors, simScenario);
    }

    @Override
    public EdgeServerManager getEdgeServerManager() {
        return new DefaultEdgeServerManager();
    }

    @Override
    public EdgeOrchestrator getEdgeOrchestrator() {
        return new SmartBuildingAppEdgeOrchestrator(orchestratorPolicy, simScenario);
    }

    @Override
    public CloudServerManager getCloudServerManager() {
        return new DefaultCloudServerManager();
    }

    @Override
    public MobileServerManager getMobileServerManager() {
        return new SmartBuildingAppServerManager(numOfSensors);
    }

    @Override
    public MobileDeviceManager getMobileDeviceManager() throws Exception {
        return new SmartBuildingAppDeviceManager();
    }
}
