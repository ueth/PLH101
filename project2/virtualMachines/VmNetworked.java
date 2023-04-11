package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public class VmNetworked extends PlainVM{
    private int bandwidth;

    public VmNetworked(int vmId, int cpuCores, int ram, OsType osType, int ssd, int bandwidth) {
        super(vmId, cpuCores, ram, osType, ssd);
        this.bandwidth = bandwidth;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }
}
