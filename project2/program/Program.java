package project2.program;

import project2.utils.GlobalProgramHandler;
import project2.virtualMachines.VM;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Program implements Serializable {
    private int pID;
    private int cpuCores;
    private int ram;
    private int ssd;
    private int gpu;
    private int bandwidth;
    private int expectedTime;
    private long executionTime;
    private long startExecTime;
    private long curExecTime;
    private double priority;
    private int failNum;
    private VM vm = null;
    private boolean finished = false;

    public Program(int pID, int cpuCores, int ram, int ssd, int gpu, int bandwidth, int expectedTime, long executionTime, long startExecTime, long curExecTime, double priority) {
        this.pID = pID;
        this.cpuCores = cpuCores;
        this.ram = ram;
        this.ssd = ssd;
        this.gpu = gpu;
        this.bandwidth = bandwidth;
        this.expectedTime = expectedTime;
        this.executionTime = executionTime;
        this.startExecTime = startExecTime;
        this.curExecTime = curExecTime;
        this.priority = priority;
        failNum = 0;
    }

    public void printStats(){
        System.out.println("[Program ID:  " + pID + ", " + cpuCores + " cores, " + ram + " GB RAM, " + ssd + " GB SSD, " + gpu + " GPU, " + bandwidth + " Gb/sec, " + " Expected Time: " + expectedTime + " secs" + ", Priority " + priority + "]");
    }

    /**
     * Exports serialized failed program
     */
    public void exportFailedProgram(){
        try {
            String dirName = "./log";
            Path dirPath = Paths.get(dirName);
            try {
                Files.createDirectories(dirPath);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Create the rejected.out file in the log directory
            String fileName = dirName + "/rejected.out";

            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
                outputStream.writeObject(this);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Serialized program data is saved in ./log/rejected.out");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isProgramDone(){
        return failNum >= 3;
    }

    public void incFailNum(){
        failNum++;

        //If there are 3 failed attempts export the program
        if(isProgramDone()) {
            finished = true;
            exportFailedProgram();
        }
        //Else we push the program at the end of the queue again
        else{
            GlobalProgramHandler.getProgramHandler().getProgramQueue().push(this);
        }
    }

    public void setVm(VM vm){
        this.vm = vm;
    }

    public VM getVm(){
        return vm;
    }

    public boolean isFinished(){
        return finished;
    }

    public boolean failed(){
        return failNum >= 3;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
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

    public int getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(int expectedTime) {
        this.expectedTime = expectedTime;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public long getStartExecTime() {
        return startExecTime;
    }

    public void setStartExecTime(long startExecTime) {
        this.startExecTime = startExecTime;
    }

    public long getCurExecTime() {
        return curExecTime;
    }

    public void setCurExecTime(long curExecTime) {
        this.curExecTime = curExecTime;
        setExecutionTime(getCurExecTime() - getStartExecTime());
    }

    public boolean hasVM(){
        return vm!=null;
    }

    public void printFinalReport(){
        if(vm == null)
            System.out.println("Program: " + pID + ", has finished in " + executionTime + " seconds.");
        else
            System.out.println("Program: " + pID + " with VM: " + getVm().getVmId() + ", has finished in " + executionTime + " seconds.");
    }

    public void checkIfFinished(){
        //Avoid checking finished programs multiple times
        if(finished == true)
            return;

        long currTime = System.currentTimeMillis()/1000;
        setCurExecTime(currTime);

        if(executionTime >= expectedTime){
            finished = true;
            //Reallocate resources
            getVm().alocateResources(-getCpuCores(), -getRam(), -getSsd(), -getGpu(), -getBandwidth());
        }
    }
}
