package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public class VmNetworkedGPU extends VmNetworked{
    int GPU;
    int alocGPU = 0;

    public VmNetworkedGPU(int vmId, int cpuCores, int ram, OsType osType, int ssd, int bandwidth, int GPU) {
        super(vmId, cpuCores, ram, osType, ssd, bandwidth);
        this.GPU = GPU;
    }

    @Override
    public int getGPU() {
        return GPU;
    }

    @Override
    public void setGPU(int GPU) {
        this.GPU = GPU;
    }

    public int getAlocGPU() {
        return alocGPU;
    }

    public void setAlocGPU(int alocGPU) {
        this.alocGPU = alocGPU;
    }

    @Override
    public void printStats() {
        System.out.println("ID: " + getVmId());
        System.out.print("[Available CPU Cores: " + getCpuCores() + " cores");
        System.out.print(", OS: " + getOsType().getName());
        System.out.print(", Ram: " + getRam() + " GB");
        System.out.print(", SSD: " + getSsd() + " GB");
        System.out.print(", GPU: " + getGPU());
        System.out.println(", Bandwidth: " + getBandwidth() + " Gb/sec]");

        System.out.print("[Allocated CPU Cores: " + getAlocCpuCores() + " cores");
        System.out.print(", Ram: " + getAlocRam() + " GB");
        System.out.print(", SSD: " + getAlocSsd() + " GB");
        System.out.print(", GPU: " + getAlocGPU());
        System.out.println(", Bandwidth: " + getAlocBandwidth() + " Gb/sec]");
    }

    @Override
    public double calculateNewVMLoad(double cpuCores, double ram, double ssd, double gpu, double bandwidth) {
        double vmload = 0;

        double allocatedCores = getAlocCpuCores();
        double allocatedRam = getAlocRam();
        double allocatedSsd = getAlocSsd();
        double allocatedBandwidth = getAlocBandwidth();
        double allocatedGPU = getAlocGPU();
        double vmCores = getCpuCores();
        double vmRam = getRam();
        double vmSsd = getSsd();
        double vmBandwidth = getBandwidth();
        double vmGPU = getGPU();

        vmload = 100*((( allocatedCores+cpuCores)/ vmCores )
                + ((allocatedRam+ram)/vmRam)
                + ((allocatedSsd+ssd)/vmSsd)
                + ((allocatedBandwidth+bandwidth)/vmBandwidth)
                + ((allocatedGPU+gpu)/vmGPU))/(5^3);

        return vmload;
    }

    @Override
    public void alocateResources(int cpuCores, int ram, int ssd, int gpu, int bandwidth) {
        setAlocCpuCores(getAlocCpuCores()+cpuCores);

        setAlocRam(getAlocRam()+ram);

        setAlocSsd(getAlocSsd()+ssd);

        setAlocBandwidth(getAlocBandwidth()+bandwidth);

        setAlocGPU(getAlocGPU()+gpu);
    }
}
