package org.vin.edgecloudsim.applications.smart_building;

import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.SimEvent;
import org.vin.edgecloudsim.core.SimManager;
import org.vin.edgecloudsim.core.SimSettings;
import org.vin.edgecloudsim.edge_client.CpuUtilizationModel_Custom;
import org.vin.edgecloudsim.edge_client.Task;
import org.vin.edgecloudsim.edge_client.mobile_process_unit.MobileVM;
import org.vin.edgecloudsim.edge_orchestrator.EdgeOrchestrator;
import org.vin.edgecloudsim.edge_server.EdgeVM;
import org.vin.edgecloudsim.utility.SimLogger;

import java.util.List;

public class SmartBuildingAppEdgeOrchestrator extends EdgeOrchestrator {
    private int numberOfHost; //used by load balancer

    public SmartBuildingAppEdgeOrchestrator(String _policy, String _simScenario) {
        super(_policy, _simScenario);
    }

    @Override
    public void initialize() {
        numberOfHost = SimSettings.getInstance().getNumOfEdgeHosts();
    }

    @Override
    public int getDeviceToOffload(Task task) {
        int result = 0;

        if(policy.equals("ONLY_EDGE")){
            result = SimSettings.GENERIC_EDGE_DEVICE_ID;
        }
        else if(policy.equals("ONLY_MOBILE")){
            result = SimSettings.MOBILE_DATACENTER_ID;
        }
        else if(policy.equals("HYBRID")){
            List<MobileVM> vmArray = SimManager.getInstance().getMobileServerManager().getVmList(task.getMobileDeviceId());
            double requiredCapacity = ((CpuUtilizationModel_Custom)task.getUtilizationModelCpu()).predictUtilization(vmArray.get(0).getVmType());
            double targetVmCapacity = (double) 100 - vmArray.get(0).getCloudletScheduler().getTotalUtilizationOfCpu(CloudSim.clock());

            if (requiredCapacity <= targetVmCapacity)
                result = SimSettings.MOBILE_DATACENTER_ID;
            else
                result = SimSettings.GENERIC_EDGE_DEVICE_ID;
        }
        else {
            SimLogger.printLine("Unknow edge orchestrator policy! Terminating simulation...");
            System.exit(0);
        }

        return result;
    }

    @Override
    public Vm getVmToOffload(Task task, int deviceId) {
        Vm selectedVM = null;

        if (deviceId == SimSettings.MOBILE_DATACENTER_ID) {
            List<MobileVM> vmArray = SimManager.getInstance().getMobileServerManager().getVmList(task.getMobileDeviceId());
            double requiredCapacity = ((CpuUtilizationModel_Custom)task.getUtilizationModelCpu()).predictUtilization(vmArray.get(0).getVmType());
            double targetVmCapacity = (double) 100 - vmArray.get(0).getCloudletScheduler().getTotalUtilizationOfCpu(CloudSim.clock());

            if (requiredCapacity <= targetVmCapacity)
                selectedVM = vmArray.get(0);
        }
        else if(deviceId == SimSettings.GENERIC_EDGE_DEVICE_ID){
            //Select VM on edge devices via Least Loaded algorithm!
            double selectedVmCapacity = 0; //start with min value
            for(int hostIndex=0; hostIndex<numberOfHost; hostIndex++){
                List<EdgeVM> vmArray = SimManager.getInstance().getEdgeServerManager().getVmList(hostIndex);
                for(int vmIndex=0; vmIndex<vmArray.size(); vmIndex++){
                    double requiredCapacity = ((CpuUtilizationModel_Custom)task.getUtilizationModelCpu()).predictUtilization(vmArray.get(vmIndex).getVmType());
                    double targetVmCapacity = (double)100 - vmArray.get(vmIndex).getCloudletScheduler().getTotalUtilizationOfCpu(CloudSim.clock());
                    if(requiredCapacity <= targetVmCapacity && targetVmCapacity > selectedVmCapacity){
                        selectedVM = vmArray.get(vmIndex);
                        selectedVmCapacity = targetVmCapacity;
                    }
                }
            }
        }
        else{
            SimLogger.printLine("Unknown device id! The simulation has been terminated.");
            System.exit(0);
        }

        return selectedVM;
    }

    @Override
    public void processEvent(SimEvent arg0) {
        // To be implemented
    }

    @Override
    public void shutdownEntity() {
        // To be implemented
    }

    @Override
    public void startEntity() {
        // To be implemented
    }

}
