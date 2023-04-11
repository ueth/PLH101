package project2.virtualMachines.vmExtras;

import project2.virtualMachines.*;

public class CreateVM  {

    public VM createVM(int vmId, int cpuCores, int ram, OsType OS, int ssd, int bandwidth, int GPU) {
        if (GPU > 0 && bandwidth > 0) {
            return new VmNetworkedGPU(vmId, cpuCores, ram, OS, ssd, bandwidth, GPU);
        } else if (GPU > 0) {
            return new VmGPU(vmId, cpuCores, ram, OS, ssd, GPU);
        } else if (bandwidth > 0) {
            return new VmNetworked(vmId, cpuCores, ram, OS, ssd, bandwidth);
        } else {
            return new PlainVM(vmId, cpuCores, ram, OS, ssd);
        }
    }
}
