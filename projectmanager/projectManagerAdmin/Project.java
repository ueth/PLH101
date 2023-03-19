package projectmanager.projectManagerAdmin;

import utils.Globals;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
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

        //Project without tasks is pending
        projectStatus = Globals.status.PENDING;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectID=" + projectID + "\t"+
                "projectName='" + projectName + '\'' + "\t"+
                "projectBudget=" + projectBudget + "\t"+
                "projectDuration=" + projectDuration + "\t"+
                "projectStatus=" + projectStatus + "\t"+
                "numOfTasks=" + numOfTasks + "\t"+
                "projectTasks=" + getProjectTasksOnString() + "}\t"+  "\n";
    }

    private String getProjectTasksOnString(){
        String sumString = "";

        if(projectTasks != null)
            for(int i=0; i<projectTasks.length; i++){
                if(projectTasks[i] != null)
                    sumString += "\n" + projectTasks[i].toString();
            }

        return sumString;
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

    private void updateTaskArray(){
        Task temp;

        // bubble sort algorithm
        for(int i=0; i < projectTasks.length-1; i++){
            for(int j=0; j < projectTasks.length-i-1; j++){
                if(projectTasks[j] == null || projectTasks[j+1] == null) continue;
                if(!Globals.validateDates(projectTasks[j].getTaskFromDate(), projectTasks[j+1].getTaskFromDate())){
                    // swap arr[j] and arr[j+1]
                    temp = projectTasks[j];
                    projectTasks[j] = projectTasks[j+1];
                    projectTasks[j+1] = temp;
                }
            }
        }

        // Update task ids to match their index in the sorted array
        // Also update their status
        for (int i = 0; i < projectTasks.length; i++) {
            if (projectTasks[i] != null) {
                if(i == 0)
                    projectTasks[i].setTaskStatus(Globals.status.ONGOING);
                else
                    projectTasks[i].setTaskStatus(Globals.status.PENDING);

                projectTasks[i].setTaskID(projectID+"."+i);
            }
        }
    }

    private void updateTaskIds(){
        //Making comparator to sort tasks based on start date
        Comparator<Task> taskComparator = (task1, task2) -> {
            if (task1 == null && task2 == null) {
                return 0;
            }
            if (task1 == null) {
                return 1;
            }
            if (task2 == null) {
                return -1;
            }
            return task1.compareTo(task2);
        };

        // Sort tasks based on start date using the custom comparator
        Arrays.sort(projectTasks, taskComparator);

        // Update task ids to match their index in the sorted array
        // Also update their status
        for (int i = 0; i < projectTasks.length; i++) {
            if (projectTasks[i] != null) {
                if(i == 0)
                    projectTasks[i].setTaskStatus(Globals.status.ONGOING);
                else
                    projectTasks[i].setTaskStatus(Globals.status.PENDING);

                projectTasks[i].setTaskID(projectID+"."+i);
            }
        }
    }

    public void insertTask(String taskTitle, Date fromDate, Date toDate, Globals.status taskStatus){
        //if there are no tasks we occupy 5 new places
        if(projectTasks == null)
            projectTasks = new Task[maxTasksPerProject];

        boolean added = false;
        for (int i = 0; i < projectTasks.length && !added; i++) {
            if (projectTasks[i] == null) {
                projectTasks[i] = new Task(projectID+"."+numOfTasks++, taskTitle, fromDate, toDate, taskStatus);
                updateTaskArray(); //Sort and update task ids
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
            numOfTasks--;
            updateTaskArray(); //Sort and update task ids
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

        updateTaskArray();
        updateProjectStatus();
    }

    private void updateProjectStatus(){
        int completed = 0;
        int ongoing = 0;
        int maxnum = 0;

        computeProjectDuration();

        //if there are no tasks the project is completed
        if(projectTasks == null) {
            projectStatus = Globals.status.PENDING;
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
        Date startDate = null;
        try {
            startDate = Globals.dateFormatter.parse("01/10/3000");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date endDate = null;
        try {
            endDate = Globals.dateFormatter.parse("01/10/2000");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i < projectTasks.length; i++){
            if(projectTasks[i] != null){
                if(!Globals.validateDates(startDate, projectTasks[i].getTaskFromDate()))//Keep the earliest date
                    startDate = projectTasks[i].getTaskFromDate();
                if(Globals.validateDates(endDate, projectTasks[i].getTaskEndDate()))//Keep the latest date
                    endDate = projectTasks[i].getTaskEndDate();
            }
        }

        projectDuration = Globals.computeDuration(startDate, endDate);
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
