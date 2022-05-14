package org.vin.edgecloudsim.edge_client.mobile_process_unit;

import org.cloudbus.cloudsim.CloudletScheduler;
import org.cloudbus.cloudsim.Vm;
import org.vin.edgecloudsim.core.SimSettings;

public class MobileVM extends Vm {
    private SimSettings.VM_TYPES type;

    public MobileVM(int id, int userId, double mips, int numberOfPes, int ram,
                    long bw, long size, String vmm, CloudletScheduler cloudletScheduler) {
        super(id, userId, mips, numberOfPes, ram, bw, size, vmm, cloudletScheduler);

        type = SimSettings.VM_TYPES.MOBILE_VM;
    }

    public SimSettings.VM_TYPES getVmType(){
        return type;
    }
}
