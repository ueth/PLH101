package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public class PlainVM extends VM{
    private int ssd;

    public PlainVM(int vmId, int cpuCores, int ram, OsType osType, int ssd) {
        super(vmId, cpuCores, ram, osType);
        this.ssd = ssd;
    }

    public int getSsd() {
        return ssd;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }
}
