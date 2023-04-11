package project2.vmHandler;

import project2.virtualMachines.*;
import project2.virtualMachines.vmExtras.CreateVM;
import project2.virtualMachines.vmExtras.OsType;

import java.util.ArrayList;
import java.util.Random;

public class VMHandler {
    private ComputerCluster cc = new ComputerCluster();
    private ArrayList<VM> vmArrayList = new ArrayList<>();
    private Random r = new Random();

    /**
     * Try to create and add a new VM in vmArrayList
     * @return false if the vm was not created, true otherwise
     */
    public boolean createVM(int cpuCores, int ram, OsType OS, int ssd, int bandwidth, int GPU){
        //Check if we have the resources
        if(!hasEnoughResources(cpuCores, ram, ssd, bandwidth, GPU)) {
            System.out.println("Computer Cluster has run out of resources.");
            return false;
        }

        //Update Cluster's resources
        updateCluster(cpuCores, ram, ssd, bandwidth, GPU);

        //Create a unique random id
        int vmId = r.nextInt(1, 100000);

        //Add a new vm in the arraylist
        vmArrayList.add(new CreateVM().createVM(vmId, cpuCores, ram, OS, ssd, bandwidth, GPU));

        return true;
    }

    /**
     * Update the resources of an existing VM
     * @return false if there are no resources or if the vmId doesn't match with any of the existing VMs' ids
     */
    public boolean updateVM(int vmId, int cpuCores, int ram, OsType OS, int ssd, int bandwidth, int GPU){
        boolean found = false;

        //Check if we have the resources
        if(!hasEnoughResources(cpuCores, ram, ssd, bandwidth, GPU)) {
            System.out.println("Computer Cluster has run out of resources.");
            return false;
        }

        //Iterate through all vms to find the one with the matching id
        for(VM vm : vmArrayList){
            if(vm.getVmId() == vmId){
                //Update Cluster's resources
                updateCluster(cpuCores, ram, ssd, bandwidth, GPU);

                vm.setCpuCores(cpuCores);
                vm.setOsType(OS);
                vm.setRam(ram);
                ((PlainVM) vm).setSsd(ssd);

                if (vm instanceof VmNetworkedGPU){
                    ((VmNetworkedGPU) vm).setBandwidth(bandwidth);
                    ((VmNetworkedGPU) vm).setGPU(GPU);
                }
                else if(vm instanceof VmNetworked)
                    ((VmNetworked) vm).setBandwidth(bandwidth);
                else if (vm instanceof VmGPU)
                    ((VmGPU) vm).setGPU(GPU);


                found = true;
                break; //If found, break out of loop
            }
        }

        if(!found)
            System.out.println("This id doesn't match with any of the VMs.");

        return found;
    }

    /**
     * Delete an existing VM
     * @return false if the vmId doesn't match with any of the existing VMs' ids
     */
    public boolean deleteVM(int vmId){
        boolean found = false;
        int cpuCores;
        int ram;
        int ssd;
        int bandwidth = 0;
        int GPU = 0;
        int index = 0;

        for(VM vm : vmArrayList){
            if(vm.getVmId() == vmId){
                cpuCores = vm.getCpuCores();
                ram = vm.getRam();
                ssd = ((PlainVM) vm).getSsd();

                if (vm instanceof VmNetworkedGPU){
                    bandwidth = ((VmNetworkedGPU) vm).getBandwidth();
                    GPU = ((VmNetworkedGPU) vm).getGPU();
                }
                else if(vm instanceof VmNetworked)
                    bandwidth = ((VmNetworked) vm).getBandwidth();
                else if (vm instanceof VmGPU)
                    bandwidth = ((VmGPU) vm).getGPU();


                //Update Cluster's resources
                updateCluster(cpuCores, ram, ssd, bandwidth, GPU);

                //Remove vm from the list
                vmArrayList.remove(index);

                found = true;
                break; //If found, break out of loop
            }
            index++;
        }

        if(!found)
            System.out.println("This id doesn't match with any of the VMs.");

        return found;
    }

    /**
     * Prints VM resources
     */
    public void printVMReport(int vmId){
        boolean found;

        //If vmId = 0 print all vm resources that are in the list
        if(vmId == 0) {
            for (VM vm : vmArrayList) {
                System.out.println("=================================================");
                System.out.println("ID: " + vm.getVmId());
                System.out.println("CPU Cores: " + vm.getCpuCores());
                System.out.println("OS: " + vm.getOsType().getName());
                System.out.println("Ram: " + vm.getRam());
                System.out.println("SSD: " + ((PlainVM) vm).getSsd());

                if (vm instanceof VmNetworkedGPU) {
                    System.out.println("Bandwidth: " + ((VmNetworkedGPU) vm).getBandwidth());
                    System.out.println("GPU: " + ((VmNetworkedGPU) vm).getGPU());
                }
                else if (vm instanceof VmNetworked)
                    System.out.println("Bandwidth: " + ((VmNetworked) vm).getBandwidth());
                else if (vm instanceof VmGPU)
                    System.out.println("GPU: " + ((VmGPU) vm).getGPU());

                //Print separator
                System.out.println("=================================================");
            }
            found = true;
        }
        else {
            for(VM vm : vmArrayList){
                if(vm.getVmId() == vmId){
                    System.out.println("=================================================");
                    System.out.println("ID: " + vm.getVmId());
                    System.out.println("CPU Cores: " + vm.getCpuCores());
                    System.out.println("OS: " + vm.getOsType().getName());
                    System.out.println("Ram: " + vm.getRam());
                    System.out.println("SSD: " + ((PlainVM) vm).getSsd());

                    if (vm instanceof VmNetworkedGPU) {
                        System.out.println("Bandwidth: " + ((VmNetworkedGPU) vm).getBandwidth());
                        System.out.println("GPU: " + ((VmNetworkedGPU) vm).getGPU());
                    }
                    else if (vm instanceof VmNetworked)
                        System.out.println("Bandwidth: " + ((VmNetworked) vm).getBandwidth());
                    else if (vm instanceof VmGPU)
                        System.out.println("GPU: " + ((VmGPU) vm).getGPU());

                    System.out.println("=================================================");
                    found = true;
                    break;
                }
            }
            found = false;
        }

        if(!found)
            System.out.println("This id doesn't match with any of the VMs.");
    }

    /**
     *
     * @return false if there are no significant resources in the cc
     */
    private boolean hasEnoughResources(int cpuCores, int ram, int ssd, int bandwidth, int GPU){
        //Check if each of the following are negative, which means that we ask for more
        if(cc.getCpuCores() - cpuCores < 0)
            return false;
        else if (cc.getRam() - ram < 0)
            return false;
        else if (cc.getSsd() - ssd < 0)
            return false;
        else if (cc.getEthernet() - bandwidth < 0)
            return false;
        else if (cc.getGpu() - GPU < 0)
            return false;

        return true;
    }

    /**
     * Update ComputerCluster's resources
     */
    private void updateCluster(int cpuCores, int ram, int ssd, int bandwidth, int GPU){
        cc.setGpu(cc.getGpu() - GPU);
        cc.setCpuCores(cc.getCpuCores() - cpuCores);
        cc.setEthernet(cc.getEthernet() - bandwidth);
        cc.setRam(cc.getRam() - ram);
        cc.setSsd(cc.getSsd() - ssd);
    }
}
