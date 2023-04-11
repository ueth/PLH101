package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public class VmGPU extends PlainVM{
    private int GPU;

    public VmGPU(int vmId, int cpuCores, int ram, OsType osType, int ssd, int GPU) {
        super(vmId, cpuCores, ram, osType, ssd);
        this.GPU = GPU;
    }

    public int getGPU() {
        return GPU;
    }

    public void setGPU(int GPU) {
        this.GPU = GPU;
    }
}
