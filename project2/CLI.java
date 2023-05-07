package project2;

import project2.loaders.ProgramLoader;
import project2.loaders.VMLoader;
import project2.program.Program;
import project2.program.ProgramAssigner;
import project2.utils.Globals;
import project2.virtualMachines.vmExtras.OsType;

import java.util.Scanner;

public class CLI {
    private static Scanner scanner = new Scanner(System.in);

    public static void operateCLI(){
        if(!Globals.hasVMFile)
            handleVMMenu();

        if(!Globals.hasProgramFile)
            handleProgramMenu();
    }

    private static void handleVMMenu(){
        int choice = 0;

        while (choice != 10){
            printMenuVM();

            //Get user's input
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    createVM();
                    break;
                case 2:
                    delete();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    printVMs();
                    break;
            }
        }
    }

    private static void handleProgramMenu(){
        int choice = 0;

        while(choice != 10){
            printMenuProgram();

            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    createProgram();
                    break;
                case 2:
                    Globals.globalProgramHandler.printProgramStats();
                    break;
            }
        }
    }

    private static void printMenuVM(){
        System.out.println("\nCreate VM - 1");
        System.out.println("Delete VM - 2");
        System.out.println("Update VM - 3");
        System.out.println("Print Report - 4");
        System.out.println("Exit - 10");
        System.out.print("Please enter your choice: ");
    }

    private static void printMenuProgram(){
        System.out.println("\nCreate Program - 1");
        System.out.println("Print Programs - 2");
        System.out.println("Exit - 10");
        System.out.print("Please enter your choice: ");
    }

    private static void createVM(){
        int cpuCores = 0;
        int ram = 0;
        String OSs;
        OsType OS;
        int ssd = 0;
        int bandwidth = 0;
        int GPU = 0;

        System.out.println("Enter CPU Cores");
        cpuCores = scanner.nextInt();

        System.out.println("Enter RAM");
        ram = scanner.nextInt();

        System.out.println("Enter OS");
        OSs = scanner.next();

        if(OSs.equalsIgnoreCase("Windows"))
            OS = OsType.WINDOWS;
        else if(OSs.equalsIgnoreCase("Ubuntu"))
            OS = OsType.UBUNTU;
        else
            OS = OsType.FEDORA;

        System.out.println("Enter SSD");
        ssd = scanner.nextInt();

        System.out.println("Enter Bandwidth");
        bandwidth = scanner.nextInt();

        System.out.println("Enter GPU");
        GPU = scanner.nextInt();

        Globals.globalVmHandler.createVM(cpuCores, ram, OS, ssd, bandwidth, GPU);
    }

    private static void delete(){
        int vmId = 0;

        System.out.println("Enter an ID of a VM to delete");
        vmId = scanner.nextInt();

        Globals.globalVmHandler.deleteVM(vmId);
    }

    private static void update(){
        int vmId = 0;
        int cpuCores = 0;
        int ram = 0;
        String OSs;
        OsType OS;
        int ssd = 0;
        int bandwidth = 0;
        int GPU = 0;

        System.out.println("Enter an ID of a VM to update");
        vmId = scanner.nextInt();

        System.out.println("Enter CPU Cores");
        cpuCores = scanner.nextInt();

        System.out.println("Enter RAM");
        ram = scanner.nextInt();

        System.out.println("Enter OS");
        OSs = scanner.next();

        if(OSs.equalsIgnoreCase("Windows"))
            OS = OsType.WINDOWS;
        else if(OSs.equalsIgnoreCase("Ubuntu"))
            OS = OsType.UBUNTU;
        else
            OS = OsType.FEDORA;

        System.out.println("Enter SSD");
        ssd = scanner.nextInt();

        System.out.println("Enter Bandwidth");
        bandwidth = scanner.nextInt();

        System.out.println("Enter GPU");
        GPU = scanner.nextInt();

        Globals.globalVmHandler.updateVM(vmId, cpuCores, ram, OS, ssd, bandwidth, GPU);
    }

    /**
     * Create and add a new program
     */
    private static void createProgram(){
        if(Globals.globalVmHandler.getTotalCores() == 0){
            System.out.println("There are no available VMs at the moment, please create at least one new VM before creating a program");
            return;
        }
        int cores;
        int ram;
        int ssd;
        int gpu;
        int bandwidth;
        int expectedTime;

        System.out.println("Enter Cores");
        cores = scanner.nextInt();

        System.out.println("Enter Ram");
        ram = scanner.nextInt();

        System.out.println("Enter SSD");
        ssd = scanner.nextInt();

        System.out.println("Enter GPU");
        gpu = scanner.nextInt();

        System.out.println("Enter Bandwidth");
        bandwidth = scanner.nextInt();

        System.out.println("Enter expected time");
        expectedTime = scanner.nextInt();

        Globals.globalProgramHandler.createProgram(cores, ram, ssd, gpu, bandwidth, expectedTime);

    }

    private static void printVMs(){
        System.out.println("Enter 0 if you want to print all VM info or enter a specific id");
        Globals.globalVmHandler.printVMReport(scanner.nextInt());
    }
}
