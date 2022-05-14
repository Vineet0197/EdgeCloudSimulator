/* -------------------------------------------------------------
 *             Copyright (c) 2022
 * Scenario Factory Interface
 * ScenarioFactoryInterface responsible for Network model, Mobility model
 * and EdgeOrchestrator.
 */
package org.vin.edgecloudsim.core;

import org.vin.edgecloudsim.cloud_server.CloudServerManager;
import org.vin.edgecloudsim.edge_client.MobileDeviceManager;
import org.vin.edgecloudsim.edge_client.mobile_process_unit.MobileServerManager;
import org.vin.edgecloudsim.edge_orchestrator.EdgeOrchestrator;
import org.vin.edgecloudsim.edge_server.EdgeServerManager;
import org.vin.edgecloudsim.mobility.MobilityModel;
import org.vin.edgecloudsim.network.NetworkModel;
import org.vin.edgecloudsim.task_generator.LoadGeneratorModel;

public interface ScenarioFactory {
    public LoadGeneratorModel getLoadGeneratorModel();
    public MobilityModel getMobilityModel();
    public NetworkModel getNetworkModel();
    public EdgeServerManager getEdgeServerManager();
    public EdgeOrchestrator getEdgeOrchestrator();
    public CloudServerManager getCloudServerManager();
    public MobileServerManager getMobileServerManager();
    public MobileDeviceManager getMobileDeviceManager() throws Exception;

}
