package project2.vmHandler;

import project2.program.Program;
import project2.virtualMachines.*;
import project2.virtualMachines.vmExtras.CreateVM;
import project2.virtualMachines.vmExtras.OsType;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        //Check if we have the resources
        if(!hasEnoughResources(cpuCores, ram, ssd, bandwidth, GPU)) {
            System.out.println("Computer Cluster has run out of resources.");
            return false;
        }

        VM vm = getVmById(vmId);

        if(vm == null)
            return false;

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

        return true;
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

        //Here we use loop instead of getVmById to keep track of the index
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
                updateCluster(-cpuCores, -ram, -ssd, -bandwidth, -GPU);

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
        //Check if the list is empty first
        if(vmArrayList.isEmpty()){
            System.out.println("There are no active VMs currently.");
            return;
        }

        //If vmId = 0 print all vm resources that are in the list
        if(vmId == 0) {
            for (VM vm : vmArrayList)
                vm.printStats();//Print vm stats
        }
        else {
            VM vm = getVmById(vmId);

            if(vm == null)
                return;

            //Print vm Stats
            vm.printStats();
        }
    }

    /**
     *
     * @return false if there are no significant resources in the cc
     */
    private boolean hasEnoughResources(int cpuCores, int ram, int ssd, int bandwidth, int GPU){
        //Check if each of the following are negative
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

    /**
     * Find any vm
     * @return the vm asked or null if the vm was not found
     */
    public VM getVmById(int vmId){
        for(VM vm : vmArrayList){
            if(vm.getVmId() == vmId)
                return vm;
        }

        System.out.println("Did not find VM with the above id");
        return null;
    }

    public void assignProgramToVM(Program program){
        boolean found = false;
        //In order to have something to compare to, we assign the first one to vmHolder
        VM vmHolder = vmArrayList.get(0);

        //iterate through all vms to find the best suited
        for(VM vm : vmArrayList){
            System.out.println("STATS " + vm.calculateNewVMLoad(program.getCpuCores(), program.getRam(), program.getSsd(), program.getGpu(), program.getBandwidth()));

            if(vm.calculateNewVMLoad(program.getCpuCores(), program.getRam(), program.getSsd(), program.getGpu(), program.getBandwidth()) <= 100
                && (vm.calculateNewVMLoad(program.getCpuCores(), program.getRam(), program.getSsd(), program.getGpu(), program.getBandwidth()) <=
                vmHolder.calculateNewVMLoad(program.getCpuCores(), program.getRam(), program.getSsd(), program.getGpu(), program.getBandwidth())))
            {
                vmHolder = vm;
                found = true;
            }
        }

        if(!found) {
            program.incFailNum(); //All happens in here, even new push
            sleep(program.getpID());
        }
        //Else if found, we allocate vmHolder's resources for the program and do not push it again in queue
        else {
            System.out.println("Program " + program.getpID() + " assigned to vm");
            long startTime = System.currentTimeMillis()/1000;
            program.setStartExecTime(startTime);
            program.setVm(vmHolder);
            vmHolder.alocateResources(program.getCpuCores(), program.getRam(), program.getSsd(), program.getGpu(), program.getBandwidth());
        }
    }

    /**
     * System sleep for 2 seconds
     */
    public void sleep(int id){
        long timeToSleep = 2L;
        TimeUnit time = TimeUnit.SECONDS;

        try {
            System.out.println("No VM found with resources for program with id: " + id + ", Sleep for 2 seconds...");
            time.sleep(timeToSleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return The sum of all cores of vms
     */
    public int getTotalCores(){
        int total = 0;

        for(VM vm : vmArrayList)
            total += vm.getCpuCores();

        return total;
    }

    /**
     *
     * @return The sum of all ram of vms
     */
    public int getTotalRam(){
        int total = 0;

        for(VM vm : vmArrayList)
            total += vm.getRam();

        return total;
    }

    /**
     *
     * @return The sum of all ssd of vms
     */
    public int getTotalSsd(){
        int total = 0;

        for(VM vm : vmArrayList)
            total += ((PlainVM) vm).getSsd();

        return total;
    }

    /**
     *
     * @return The sum of all gpu of vms
     */
    public int getTotalGPU(){
        int total = 0;

        for(VM vm : vmArrayList)
            total += (vm instanceof VmGPU) ? ((VmGPU) vm).getGPU() : (vm instanceof VmNetworkedGPU) ? ((VmNetworkedGPU) vm).getGPU() : 0;

        return total;
    }

    /**
     *
     * @return The sum of all bandwidth of vms
     */
    public int getTotalBandwidth(){
        int total = 0;

        for(VM vm : vmArrayList)
            total += (vm instanceof VmNetworkedGPU) ? ((VmNetworkedGPU) vm).getBandwidth() : (vm instanceof VmNetworked) ? ((VmNetworked) vm).getBandwidth() : 0;

        return total;
    }

    public ArrayList<VM> getVmArrayList(){
        return vmArrayList;
    }
}
