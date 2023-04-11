package project2.vmHandler;

import project2.utils.Globals;

public final class ComputerCluster {
    private int cpuCores = Globals.cpuCoreNum;
    private int ram = Globals.ramNum;
    private int ssd = Globals.ssdNum;
    private int gpu = Globals.gpuNum;
    private int ethernet = Globals.ethernetBandNum;

    public ComputerCluster() {
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

    public int getSsd() {
        return ssd;
    }

    public void setSsd(int ssd) {
        this.ssd = ssd;
    }

    public int getGpu() {
        return gpu;
    }

    public void setGpu(int gpu) {
        this.gpu = gpu;
    }

    public int getEthernet() {
        return ethernet;
    }

    public void setEthernet(int ethernet) {
        this.ethernet = ethernet;
    }
}
