package org.vin.edgecloudsim.edge_client.mobile_process_unit;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.VmAllocationPolicy;

import java.util.List;

public class DefaultMobileServerManager extends MobileServerManager {
    public DefaultMobileServerManager() {

    }

    @Override
    public void initialize() {
    }

    @Override
    public VmAllocationPolicy getVmAllocationPolicy(List<? extends Host> list, int dataCenterIndex) {
        return new MobileVmAllocationPolicy_Custom(list, dataCenterIndex);
    }

    @Override
    public void startDatacenters() throws Exception {
        //local computation is not supported in default Mobile Device Manager
    }

    @Override
    public void terminateDatacenters() {
        //local computation is not supported in default Mobile Device Manager
    }

    @Override
    public void createVmList(int brokerId) {
        //local computation is not supported in default Mobile Device Manager
    }

    @Override
    public double getAvgUtilization() {
        //local computation is not supported in default Mobile Device Manager
        return 0;
    }
}
