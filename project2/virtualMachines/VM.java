package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public abstract class VM {
    private int vmId;
    private int cpuCores;
    private int ram;
    private OsType osType;

    public VM(int vmId, int cpuCores, int ram, OsType osType) {
        this.vmId = vmId;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.osType = osType;
    }

    public int getVmId() {
        return vmId;
    }

    public void setVmId(int vmId) {
        this.vmId = vmId;
    }

    public int getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(int cpuCores) {
        this.cpuCores = cpuCores;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public OsType getOsType() {
        return osType;
    }

    public void setOsType(OsType osType) {
        this.osType = osType;
    }
}
