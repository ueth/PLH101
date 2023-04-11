package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public abstract class VM {
    private int vmId;
    private int cpuCores;
    private int ram;
    private OsType osType;
    private int alocCpuCores;
    private int alocRam;

    public VM(int vmId, int cpuCores, int ram, OsType osType) {
        this.vmId = vmId;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.osType = osType;
    }

    public abstract void printStats();
    public abstract double calculateCurrentVMLoad();
    public abstract double calculateNewVMLoad(double cpuCores, double ram, double ssd, double gpu, double bandwidth);
    public abstract double calculateNewVMLoad(int cpuCores, int ram, int ssd, int gpuOrBandwidth);
    public abstract double calculateNewVMLoad(int cpuCores, int ram, int ssd);
    public abstract void alocateResources(int cpuCores, int ram, int ssd, int gpu, int bandwidth);
    public int getAlocCpuCores() {
        return alocCpuCores;
    }

    public void setAlocCpuCores(int alocCpuCores) {
        this.alocCpuCores = alocCpuCores;
    }

    public int getAlocRam() {
        return alocRam;
    }

    public void setAlocRam(int alocRam) {
        this.alocRam = alocRam;
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
