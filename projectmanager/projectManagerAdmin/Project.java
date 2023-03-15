package projectmanager.projectManagerAdmin;

import utils.Globals;

import java.util.Arrays;
import java.util.Date;

import static utils.Globals.maxProjectsPerEmployee;
import static utils.Globals.maxTasksPerProject;

public class Project {
    private int numOfProjects;
    private int projectID;
    private String projectName;
    private double projectBudget;
    private int projectDuration;
    private Globals.status projectStatus;
    private Task [] projectTasks;
    private int numOfTasks;

    Project(String name, double budget){
        projectName = name;
        projectBudget = budget;
    }

    @Override
    public String toString() {
        return "Project{" +
                "numOfProjects=" + numOfProjects + "\t"+
                "projectID=" + projectID + "\t"+
                "projectName='" + projectName + '\'' + "\t"+
                "projectBudget=" + projectBudget + "\t"+
                "projectDuration=" + projectDuration + "\t"+
                "projectStatus=" + projectStatus + "\t"+
                "projectTasks=" + Arrays.toString(projectTasks) + "\t"+
                "numOfTasks=" + numOfTasks + "\t"+
                '}';
    }

    protected int findTaskById(String tID){
        for(int i = 0; i<projectTasks.length; i++){
            if(projectTasks[i] != null && projectTasks[i].getTaskID().equals(tID)){
                System.out.println("Task with id: " + tID + " found.");
                return i;
            }
        }

        System.out.println("No tasks with this id found.");
        return -1;
    }

    public void insertTask(String taskTitle, Date fromDate, Date toDate, Globals.status taskStatus){
        //if there are no tasks we occupy 5 new places
        if(projectTasks == null)
            projectTasks = new Task[maxTasksPerProject];

        boolean added = false;
        for (int i = 0; i < projectTasks.length && !added; i++) {
            if (projectTasks[i] == null) {
                projectTasks[i] = new Task(projectID+"."+i, taskTitle, fromDate, toDate, taskStatus);
                added = true;
                updateProjectStatus();
            }
        }

        if (!added) {
            System.out.println("No empty space available to add a new task.");
        }
    }

    protected void deleteTask(int taskIdx){
        if (taskIdx >= 0 && taskIdx < projectTasks.length && projectTasks[taskIdx] != null) {
            projectTasks[taskIdx] = null;
            System.out.println("Task removed from index " + taskIdx);
            updateProjectStatus();
        } else {
            System.out.println("Invalid index or no tasks exists at the given index.");
        }
    }

    void updateTask(String tID, String title, Date newFrom, Date newTo, Globals.status stat){
        // Find the task with the given tID
        Task taskToUpdate = null;
        for (int i = 0; i < projectTasks.length; i++) {
            if (projectTasks[i] != null && projectTasks[i].getTaskID().equals(tID)) {
                taskToUpdate = projectTasks[i];
                break;
            }
        }

        if (taskToUpdate == null) {
            System.out.println("No task found with the given ID.");
            return;
        }

        // Update the task with the given fields
        if (title != null) {
            taskToUpdate.setTaskTitle(title);
        }

        if (newFrom != null) {
            taskToUpdate.setTaskFromDate(newFrom);
        }

        if (newTo != null) {
            taskToUpdate.setTaskEndDate(newTo);
        }

        if (stat != null) {
            taskToUpdate.setTaskStatus(stat);
        }

        updateProjectStatus();
    }

    private void updateProjectStatus(){
        int completed = 0;
        int ongoing = 0;
        int maxnum = 0;

        computeProjectDuration();

        //if there are no tasks the project is completed
        if(projectTasks == null) {
            projectStatus = Globals.status.COMPLETED;
        }
        else{
            for(int i=0; i < projectTasks.length; i++){
                if(projectTasks[i] == null)
                    continue;

                maxnum++;

                //increase each variable accordingly
                if(projectTasks[i].getTaskStatus() == Globals.status.COMPLETED)
                    completed++;
                else
                    ongoing++;
            }
            if(completed == maxnum)//if all tasks are completed the project is complete
                projectStatus = Globals.status.COMPLETED;
            else if(ongoing >= 1)//if there is at least one ongoing task then the project is ongoing
                projectStatus = Globals.status.ONGOING;
            else//else the project is pending
                projectStatus = Globals.status.PENDING;
        }
    }

    private void computeProjectDuration(){
        int projectDuration = 0;

        for(int i=0; i < projectTasks.length; i++){
            if(projectTasks[i] != null){
                if(projectTasks[i].getTaskDuration() > projectDuration)
                    projectDuration = projectTasks[i].getTaskDuration();
            }
        }
    }

    public String displayTasks(){
        String taskSum = "";
        for(int i=0; i < projectTasks.length; i++){
            if(projectTasks[i] != null){
                taskSum += projectTasks[i].toString() + "\n";
            }
        }
        return taskSum;
    }

    public int getNumOfProjects() {
        return numOfProjects;
    }

    public void setNumOfProjects(int numOfProjects) {
        this.numOfProjects = numOfProjects;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public double getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(double projectBudget) {
        this.projectBudget = projectBudget;
    }

    public int getProjectDuration() {
        return projectDuration;
    }

    public void setProjectDuration(int projectDuration) {
        this.projectDuration = projectDuration;
    }

    public Globals.status getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(Globals.status projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Task[] getProjectTasks() {
        return projectTasks;
    }

    public void setProjectTasks(Task[] projectTasks) {
        this.projectTasks = projectTasks;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public void setNumOfTasks(int numOfTasks) {
        this.numOfTasks = numOfTasks;
    }
}
