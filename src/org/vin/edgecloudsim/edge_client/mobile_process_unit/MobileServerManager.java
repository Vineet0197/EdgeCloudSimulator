package org.vin.edgecloudsim.edge_client.mobile_process_unit;

import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.VmAllocationPolicy;

import java.util.ArrayList;
import java.util.List;

public abstract class MobileServerManager {
    protected Datacenter localDatacenter;
    protected List<List<MobileVM>> vmList;

    public MobileServerManager() {
        vmList = new ArrayList<List<MobileVM>>();
    }

    public List<MobileVM> getVmList(int hostId){
        if(vmList.size() > hostId)
            return vmList.get(hostId);
        else
            return null;
    }

    public Datacenter getDatacenter(){
        return localDatacenter;
    }

    /*
     * initialize edge server manager if needed
     */
    public abstract void initialize();

    /*
     * provides abstract Vm Allocation Policy for Mobile Datacenters
     */
    public abstract VmAllocationPolicy getVmAllocationPolicy(List<? extends Host> list, int dataCenterIndex);

    /*
     * Starts Datacenters
     */
    public abstract void startDatacenters() throws Exception;

    /*
     * Terminates Datacenters
     */
    public abstract void terminateDatacenters();
    /*
     * Creates VM List
     */
    public abstract void createVmList(int brokerId);

    /*
     * returns average utilization of all VMs
     */
    public abstract double getAvgUtilization();
}
