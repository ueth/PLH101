package project2;

import project2.loaders.ProgramLoader;
import project2.loaders.VMLoader;
import project2.program.ProgramAssigner;

public final class App {
    public static void main(String [] args){
        //Try to load vms-programs from config files
        VMLoader.loadVMS();
        ProgramLoader.loadPrograms();
        //Operate CLI if configs were not found
        CLI.operateCLI();
        //Start the assignments
        ProgramAssigner.programAssignToVM();
    }
}
