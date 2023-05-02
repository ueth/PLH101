package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public class PlainVM extends VM{
    private int ssd;
    private int alocSsd;

    public PlainVM(int vmId, int cpuCores, int ram, OsType osType, int ssd) {
        super(vmId, cpuCores, ram, osType);
        this.ssd = ssd;
    }
    @Override
    public int getSsd() {
        return ssd;
    }

    @Override
    public void setGPU(int GPU) {

    }

    @Override
    public int getGPU() {
        return 0;
    }

    @Override
    public void setBandwidth(int Bandwidth) {

    }

    @Override
    public int getBandwidth() {
        return 0;
    }

    @Override
    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public int getAlocSsd() {
        return alocSsd;
    }

    public void setAlocSsd(int alocSsd) {
        this.alocSsd = alocSsd;
    }

    @Override
    public void printStats() {
        System.out.println("ID: " + getVmId());
        System.out.print("[Available CPU Cores: " + getCpuCores() + " cores");
        System.out.print(", OS: " + getOsType().getName());
        System.out.print(", Ram: " + getRam() + " GB");
        System.out.println(", SSD: " + getSsd() + " GB]");

        System.out.print("[Allocated CPU Cores: " + getAlocCpuCores() + " cores");
        System.out.print(", Ram: " + getAlocRam() + " GB");
        System.out.println(", SSD: " + getAlocSsd() + " GB]");
    }

    @Override
    public double calculateNewVMLoad(double cpuCores, double ram, double ssd, double gpu, double bandwidth) {
        double vmload = 0;
        double allocatedCores = getAlocCpuCores();
        double allocatedRam = getAlocRam();
        double allocatedSsd = getAlocSsd();
        double vmCores = getCpuCores();
        double vmRam = getRam();
        double vmSsd = getSsd();

        vmload = 100*(
                  ((allocatedCores+cpuCores)/vmCores)
                + ((allocatedRam+ram)/vmRam)
                + ((allocatedSsd+ssd)/vmSsd)
                     )/(4^3);

        return vmload;
    }

    @Override
    public void alocateResources(int cpuCores, int ram, int ssd, int gpu, int bandwidth) {
        setAlocCpuCores(getAlocCpuCores()+cpuCores);

        setAlocRam(getAlocRam()+ram);

        setAlocSsd(getAlocSsd()+ssd);
    }
}
