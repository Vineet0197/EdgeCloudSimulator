package org.vin.edgecloudsim.edge_client.mobile_process_unit;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;

import java.util.List;

public class MobileHost extends Host {
    private int mobileDeviceId;

    public MobileHost(int id, RamProvisioner ramProvisioner,
                      BwProvisioner bwProvisioner, long storage,
                      List<? extends Pe> peList, VmScheduler vmScheduler) {
        super(id, ramProvisioner, bwProvisioner, storage, peList, vmScheduler);

    }

    public void setMobileDeviceId(int _mobileDeviceId){
        mobileDeviceId=_mobileDeviceId;
    }

    public int getMobileDeviceId(){
        return mobileDeviceId;
    }
}
