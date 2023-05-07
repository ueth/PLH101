package project2.program;

import project2.utils.Globals;

public final class ProgramAssigner {

    public static void programAssignToVM(){
        //First push programs in queue
        Globals.globalProgramHandler.pushProgramsInQueue();

        while(true){
            //Check if a running program has finished
            for(Program finishedProgram : Globals.globalProgramHandler.getProgramArrayList())
                //If finished, the program will give back the resources to the VM
                if(finishedProgram.hasVM())
                    finishedProgram.checkIfFinished();

            if(Globals.globalProgramHandler.areAllProgramsFinished()){
                //Print final report of programs
                for(Program program : Globals.globalProgramHandler.getProgramArrayList())
                    program.printFinalReport();
                System.out.println("Queue is empty, all programs are either finished or failed");
                break;
            }
            else if(!Globals.globalProgramHandler.getProgramQueue().isEmpty()){
                Program program = Globals.globalProgramHandler.getProgramQueue().pop();
                Globals.globalVmHandler.assignProgramToVM(program);
            }
        }
    }

}
