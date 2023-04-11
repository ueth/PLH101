package project2.program;

import project2.utils.BoundedQueue;
import project2.utils.GlobalVMHandler;
import project2.utils.Globals;
import java.util.ArrayList;
import java.util.Random;

public class ProgramHandler {
    private ArrayList<Program> programArrayList = new ArrayList<>();
    private Random r = new Random();
    private static BoundedQueue<Program> programQueue = new BoundedQueue<>(Globals.queueMaxSize);

    /**
     * Create and add a new program into programArrayList
     */
    public void createProgram(int cpuCores, int ram, int ssd, int gpu, int bandwidth, int expectedTime){
        int pID = r.nextInt(1, 10000);
        double prio = calculatePriority(cpuCores, ram, ssd, gpu, bandwidth);

        programArrayList.add(new Program(pID, cpuCores, ram, ssd, gpu, bandwidth, expectedTime, 0, 0, 0, prio));

        //Sort the array based on priority (only when we have 2 or more)
        if(programArrayList.size() > 1)
            insertionSort(programArrayList);
    }

    /**
     * Clalculate the priority of a program
     * @return priority
     */
    private double calculatePriority(double cpuCores, double ram, double ssd, double gpu, double bandwidth){
        double priority = 0;
        double totalCores = GlobalVMHandler.getVmh().getTotalCores();
        double totalRam = GlobalVMHandler.getVmh().getTotalRam();
        double totalSsd = GlobalVMHandler.getVmh().getTotalSsd();
        double totalGPU = GlobalVMHandler.getVmh().getTotalGPU();
        double totalBandwidth = GlobalVMHandler.getVmh().getTotalBandwidth();

        //Avoid dividing with zero
        priority = (cpuCores/totalCores) + (ram/totalRam) + (ssd/totalSsd) +
                (totalGPU == 0 ? gpu : (gpu/totalGPU)) + (totalBandwidth == 0 ? bandwidth : bandwidth/totalBandwidth);

        return priority;
    }

    /**
     * Sort an arraylist based on priority
     */
    public static void insertionSort(ArrayList<Program> programs) {
        for (int i = 1; i < programs.size(); i++) {
            Program prog = programs.get(i);
            int j = i - 1;

            while (j >= 0 && programs.get(j).getPriority() < prog.getPriority()) {
                programs.set(j + 1, programs.get(j));
                j--;
            }

            programs.set(j + 1, prog);
        }
    }

    /**
     * @return a Program from programArrayList by searching its id
     */
    public Program getProgramById(int pID){
        if(programArrayList.isEmpty()) {
            System.out.println("The Program List is empty");
            return null;
        }

        for(Program program : programArrayList)
            if(program.getpID() == pID)
                return program;

        System.out.println("No program found with this specific id.");

        return null;
    }

    public boolean areAllProgramsFinished(){
        for(Program program : programArrayList){
            if(!program.isFinished())
                return false;
        }
        return true;
    }

    /**
     * Prints all program stats
     */
    public void printProgramStats(){
        if(programArrayList.isEmpty()) {
            System.out.println("The Program List is empty");
            return;
        }

        for(Program program : programArrayList)
            program.printStats();
    }

    public ArrayList<Program> getProgramArrayList(){
        return programArrayList;
    }

    /**
     * Push all available programs in the Queue
     */
    public void pushProgramsInQueue(){
        for(Program program : getProgramArrayList())
            programQueue.push(program);
    }

    public BoundedQueue<Program> getProgramQueue(){
        return programQueue;
    }
}
