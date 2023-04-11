package project2.utils;

import project2.program.ProgramHandler;


public class GlobalProgramHandler {
    private static ProgramHandler ph;

    public static ProgramHandler createProgramHandler(){
        return ph = new ProgramHandler();
    }

    public static ProgramHandler getProgramHandler() {
        return ph;
    }
}
