package project2.utils;

import project2.program.ProgramHandler;
import project2.vmHandler.VMHandler;

public final class Globals {
    public static final int cpuCoreNum = 128;
    public static final int ramNum = 256;
    public static final int ssdNum = 2048;
    public static final int gpuNum = 8;
    public static final int ethernetBandNum = 320;
    public static final int queueMaxSize = 100;
    //Flags to know if we load vms and programs from configs
    public static boolean hasVMFile = false;
    public static boolean hasProgramFile = false;
    public static final ProgramHandler globalProgramHandler = new ProgramHandler();
    public static final VMHandler globalVmHandler = new VMHandler();
}
