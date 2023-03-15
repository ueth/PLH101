package projectmanager.projectManagerAdmin;

import utils.Globals;

import java.util.Arrays;
import java.util.Date;

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

    private int findTaskById(String tID){
        return 0;
    }

    private void insertTask(String taskTitle, Date fromDate, Date toDate, Globals.status taskStatus){

    }

    private void deleteTask(int taskIdx){

    }

    void updateTask(String tID, String title, Date newFrom, Date newTo, Globals.status stat){

    }

    private void updateProjectStatus(){

    }

    private void computeProjectDuration(){

    }

    public String displayTasks(){
        return null;
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
