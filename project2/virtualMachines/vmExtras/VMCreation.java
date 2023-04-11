package project2.virtualMachines.vmExtras;

import project2.virtualMachines.VM;

public interface VMCreation {
    VM createVM(int vmId, int cpuCores, int ram, OsType OS, int ssd, int bandwidth, int GPU);
}
