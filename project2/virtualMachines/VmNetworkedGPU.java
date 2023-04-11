package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public class VmNetworkedGPU extends VmNetworked{
    int GPU;

    public VmNetworkedGPU(int vmId, int cpuCores, int ram, OsType osType, int ssd, int bandwidth, int GPU) {
        super(vmId, cpuCores, ram, osType, ssd, bandwidth);
        this.GPU = GPU;
    }

    public int getGPU() {
        return GPU;
    }

    public void setGPU(int GPU) {
        this.GPU = GPU;
    }
}
