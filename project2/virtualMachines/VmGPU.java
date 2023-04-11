package project2.virtualMachines;

import project2.virtualMachines.vmExtras.OsType;

public class VmGPU extends PlainVM{
    private int GPU;
    private int alocGPU;

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
        System.out.println(", GPU: " + getGPU() + "]");

        System.out.print("[Allocated CPU Cores: " + getAlocCpuCores() + " cores");
        System.out.print(", Ram: " + getAlocRam() + " GB");
        System.out.print(", SSD: " + getAlocSsd() + " GB");
        System.out.println(", GPU: " + getAlocGPU() + "]");
    }

    @Override
    public double calculateCurrentVMLoad() {
        double vmload = 0;
        vmload = ((getAlocCpuCores()/getCpuCores()) + (getAlocRam()/getRam()) + (getAlocSsd()/getSsd()) + (getAlocGPU()/getGPU()))/(4^3);
        return vmload;
    }

    @Override
    public double calculateNewVMLoad(double cpuCores, double ram, double ssd, double gpu, double bandwidth) {
        double vmload = 0;
        double allocatedCores = getAlocCpuCores();
        double allocatedRam = getAlocRam();
        double allocatedSsd = getAlocSsd();
        double allocatedGPU = getAlocGPU();
        double vmCores = getCpuCores();
        double vmRam = getRam();
        double vmSsd = getSsd();
        double vmGPU = getGPU();

        vmload = ((( allocatedCores+cpuCores)/ vmCores )
                + ((allocatedRam+ram)/vmRam)
                + ((allocatedSsd+ssd)/vmSsd)
                + ((allocatedGPU+gpu)/vmGPU))/(4);

        return vmload;
    }

    @Override
    public void alocateResources(int cpuCores, int ram, int ssd, int gpu, int bandwidth) {
        setAlocCpuCores(getAlocCpuCores()+cpuCores);

        setAlocRam(getAlocRam()+ram);

        setAlocSsd(getAlocSsd()+ssd);

        setAlocGPU(getAlocGPU()+gpu);
    }
}
