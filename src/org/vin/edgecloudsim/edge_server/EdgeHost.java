package org.vin.edgecloudsim.edge_server;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.VmScheduler;
import org.cloudbus.cloudsim.provisioners.BwProvisioner;
import org.cloudbus.cloudsim.provisioners.RamProvisioner;
import org.vin.edgecloudsim.utility.Location;

import java.util.List;

public class EdgeHost extends Host {
    private Location location;

    public EdgeHost(int id, RamProvisioner ramProvisioner,
                    BwProvisioner bwProvisioner, long storage,
                    List<? extends Pe> peList, VmScheduler vmScheduler) {
        super(id, ramProvisioner, bwProvisioner, storage, peList, vmScheduler);

    }

    public void setPlace(Location _location){
        location=_location;
    }

    public Location getLocation(){
        return location;
    }
}
