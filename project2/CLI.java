package project2;

import project2.virtualMachines.vmExtras.OsType;
import project2.vmHandler.VMHandler;

import java.util.Scanner;

public class CLI {
    private static VMHandler vmh = new VMHandler();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String [] args){
        operateCLI();
    }

    private static void operateCLI(){
        int choice = 0;

        System.out.println("___________Command Line Interface___________");

        while (choice != 5){
            printMenu();

            //Get user's input
            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    create();
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

    private static void printMenu(){
        System.out.println("Create VM - 1");
        System.out.println("Delete VM - 2");
        System.out.println("Update VM - 3");
        System.out.println("Print Report - 4");
        System.out.println("Exit - 5");
        System.out.print("Please enter your choice: ");
    }

    private static void create(){
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

        vmh.createVM(cpuCores, ram, OS, ssd, bandwidth, GPU);
    }

    private static void delete(){
        int vmId = 0;

        System.out.println("Enter an ID of a VM to delete");
        vmId = scanner.nextInt();

        vmh.deleteVM(vmId);
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

        vmh.updateVM(vmId, cpuCores, ram, OS, ssd, bandwidth, GPU);
    }

    private static void printVMs(){
        System.out.println("Enter 0 if you want to print all VM info or enter a specific id");
        vmh.printVMReport(scanner.nextInt());
    }
}
