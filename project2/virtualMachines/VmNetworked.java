package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public class VmNetworked extends PlainVM{
    private int bandwidth;
    private int alocBandwidth = 0;

    public VmNetworked(int vmId, int cpuCores, int ram, OsType osType, int ssd, int bandwidth) {
        super(vmId, cpuCores, ram, osType, ssd);
        this.bandwidth = bandwidth;
    }

    @Override
    public int getBandwidth() {
        return bandwidth;
    }

    @Override
    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    public int getAlocBandwidth() {
        return alocBandwidth;
    }

    public void setAlocBandwidth(int alocBandwidth) {
        this.alocBandwidth = alocBandwidth;
    }

    @Override
    public void printStats() {
        System.out.println("ID: " + getVmId());
        System.out.print("[Available CPU Cores: " + getCpuCores() + " cores");
        System.out.print(", OS: " + getOsType().getName());
        System.out.print(", Ram: " + getRam() + " GB");
        System.out.print(", SSD: " + getSsd() + " GB");
        System.out.println(", Bandwidth: " + getBandwidth() + " Gb/sec]");

        System.out.print("[Allocated CPU Cores: " + getAlocCpuCores() + " cores");
        System.out.print(", Ram: " + getAlocRam() + " GB");
        System.out.print(", SSD: " + getAlocSsd() + " GB");
        System.out.println(", GPU: " + getAlocBandwidth() + " Gb/sec]");
    }

    @Override
    public double calculateNewVMLoad(double cpuCores, double ram, double ssd, double gpu, double bandwidth) {
        double vmload = 0;

        double allocatedCores = getAlocCpuCores();
        double allocatedRam = getAlocRam();
        double allocatedSsd = getAlocSsd();
        double allocatedBandwidth = getAlocBandwidth();
        double vmCores = getCpuCores();
        double vmRam = getRam();
        double vmSsd = getSsd();
        double vmBandwidth = getBandwidth();

        vmload = 100*((( allocatedCores+cpuCores)/ vmCores )
                + ((allocatedRam+ram)/vmRam)
                + ((allocatedSsd+ssd)/vmSsd)
                + ((allocatedBandwidth+bandwidth)/vmBandwidth))/(4^3);

        return vmload;
    }

    @Override
    public void alocateResources(int cpuCores, int ram, int ssd, int gpu, int bandwidth) {
        setAlocCpuCores(getAlocCpuCores()+cpuCores);

        setAlocRam(getAlocRam()+ram);

        setAlocSsd(getAlocSsd()+ssd);

        setAlocBandwidth(getAlocBandwidth()+bandwidth);
    }
}
