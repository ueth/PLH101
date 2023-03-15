package projectmanager.projectManagerAdmin;

import utils.Globals;

import java.util.Date;

public class IMS {
    private Project [] myProjects;
    private int  numOfProjects;
    private Employee [] myEmployees;
    private int numOfEmployees;

    public IMS(int maxProjects, int maxEmployees){

    }

    private int findEmployeeByName(String empName){
        return 0;
    }

    public int findProjectByName(String projName){
        return 0;
    }
    public Project getProjectByName(String projName){
        return null;
    }

    private int findEmployeeById(String empID){
        return 0;
    }

    public int findProjectById(int projectID) {
        return 0;
    }

    public Project getProjectById(int projId) {
        return null;
    }

    public void insertEmployee(String id, String name, String empOfficeNo, String empOfficePhone) {

    }

    public void insertProject(String name, double budget){

    }

    public void insertProjectTask(int projID, String taskTitle, Date start, Date end, Globals.status stat) {

    }

    public void updateEmployee(String eid, String empName, String empOfficeNo, String empOfficePhone) {

    }

    public void updateProject(int pID, String title, double budget){

    }

    public void updateProjectTask(String taskId, String tTitle, Date tFromDate, Date tToDate, Globals.status stat ){

    }

    public void deleteEmployee(String empID){

    }

    public void deleteProject(int projID){

    }

    public void deleteTask(String taskID){

    }

    public void assignDevPosition(String empID, Globals.programmingLanguages[] languages) {

    }

    public void assignManagerPosition(String empID) {

    }

    public void assignEmpToProject (String empId, int projId){

    }

    public void ceaseEmpFromProject (String empId, int projId){

    }

    public String displayEmployees(){
        return null;
    }

    public String displayProjects() {
        return null;
    }

    public Project[] getMyProjects() {
        return myProjects;
    }

    public void setMyProjects(Project[] myProjects) {
        this.myProjects = myProjects;
    }

    public int getNumOfProjects() {
        return numOfProjects;
    }

    public void setNumOfProjects(int numOfProjects) {
        this.numOfProjects = numOfProjects;
    }

    public Employee[] getMyEmployees() {
        return myEmployees;
    }

    public void setMyEmployees(Employee[] myEmployees) {
        this.myEmployees = myEmployees;
    }

    public int getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setNumOfEmployees(int numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }
}
