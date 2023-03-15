package projectmanager.projectManagerAdmin;

import utils.Globals;

import java.util.Date;
import java.util.StringTokenizer;

public class IMS {
    private Project [] myProjects;
    private int  numOfProjects;
    private Employee [] myEmployees;
    private int numOfEmployees;

    public IMS(int maxProjects, int maxEmployees){
        numOfProjects = maxProjects;
        numOfEmployees = maxEmployees;
    }

    private int findEmployeeByName(String empName){
        for(int i=0; i<myEmployees.length; i++){
            if(myEmployees[i] != null && myEmployees[i].getEmpName().equals(empName))
                return i;
        }
        return -1;
    }

    public int findProjectByName(String projName){
        for(int i=0; i<myProjects.length; i++){
            if(myProjects[i] != null && myProjects[i].getProjectName().equals(projName))
                return i;
        }
        return -1;
    }
    public Project getProjectByName(String projName){
        for(int i=0; i<myProjects.length; i++){
            if(myProjects[i] != null && myProjects[i].getProjectName().equals(projName))
                return myProjects[i];
        }
        return null;
    }

    private int findEmployeeById(String empID){
        for(int i=0; i<myEmployees.length; i++){
            if(myEmployees[i] != null && myEmployees[i].getEmpID().equals(empID))
                return i;
        }
        return -1;
    }

    public int findProjectById(int projectID) {
        for(int i=0; i<myProjects.length; i++){
            if(myProjects[i] != null && myProjects[i].getProjectID() == projectID)
                return i;
        }
        return -1;
    }

    public Project getProjectById(int projId) {
        for(int i=0; i<myProjects.length; i++){
            if(myProjects[i] != null && myProjects[i].getProjectID() == projId)
                return myProjects[i];
        }
        return null;
    }

    public void insertEmployee(String id, String name, String empOfficeNo, String empOfficePhone) {
        if(myEmployees == null)
            myEmployees = new Employee[numOfEmployees];

        boolean added = false;
        for (int i = 0; i < myEmployees.length && !added; i++) {
            if (myEmployees[i] == null) {
                myEmployees[i] = new Employee(id, name, empOfficeNo, empOfficePhone);
                added = true;
            }
        }

        if (!added) {
            System.out.println("No empty space available to add a new employee.");
        }
    }

    public void insertProject(String name, double budget){
        if(myProjects == null)
            myProjects = new Project[numOfProjects];

        boolean added = false;
        for (int i = 0; i < myProjects.length && !added; i++) {
            if (myProjects[i] == null) {
                myProjects[i] = new Project(name, budget);
                myProjects[i].setProjectID(i);
                added = true;
            }
        }

        if (!added) {
            System.out.println("No empty space available to add a new project.");
        }
    }

    public void insertProjectTask(int projID, String taskTitle, Date start, Date end, Globals.status stat) {
        Project p = getProjectById(projID);

        if(p!=null)
            p.insertTask(taskTitle, start, end, stat);
        else
            System.out.println("Did not find project with this specific id in insertProjectTask.");
    }

    public void updateEmployee(String eid, String empName, String empOfficeNo, String empOfficePhone) {

        int index = findEmployeeById(eid);

        if(index == -1){
            System.out.println("Did not find employee with this specific id");
            return;
        }

        if (empName != null) {
            myEmployees[index].setEmpName(empName);
        }
        if (empOfficeNo != null) {
            myEmployees[index].setEmpOfficeNo(empOfficeNo);
        }
        if (empOfficePhone != null) {
            myEmployees[index].setEmpOfficePhone(empOfficePhone);
        }
    }

    public void updateProject(int pID, String title, double budget){
        Project p = getProjectById(pID);

        if(p == null){
            System.out.println("Did not find project with this specific id in updateProject");
            return;
        }

        if(title != null)
            p.setProjectName(title);

        if(budget != 0){
            p.setProjectBudget(budget);
        }
    }

    public void updateProjectTask(String taskId, String tTitle, Date tFromDate, Date tToDate, Globals.status stat ){
        //first we have to extract the project id from the taskId
        StringTokenizer st = new StringTokenizer(taskId, ".");
        int projID = Integer.valueOf(st.nextToken());

        Project p = getProjectById(projID);

        if(p == null){
            System.out.println("Did not find project with this specific id in updateProjectTask");
            return;
        }

        p.updateTask(taskId, tTitle, tFromDate, tToDate, stat);
    }

    public void deleteEmployee(String empID){
        int index = findEmployeeById(empID);

        if(index == -1){
            System.out.println("Did not find employees with this specific id");
            return;
        }

        myEmployees[index] = null;

    }

    public void deleteProject(int projID){
        int index = findProjectById(projID);

        if(index == -1){
            System.out.println("Did not find projects with this specific id");
            return;
        }

        myProjects[index] = null;
    }

    public void deleteTask(String taskID){
        StringTokenizer st = new StringTokenizer(taskID, ".");
        int projID = Integer.valueOf(st.nextToken());

        Project p = getProjectById(projID);

        if(p == null){
            System.out.println("Did not find project with this specific id in deleteTask");
            return;
        }

        p.deleteTask(p.findTaskById(taskID));
    }

    public void assignDevPosition(String empID, Globals.programmingLanguages[] languages) {
        int index = 0;

        index = findEmployeeById(empID);

        if(index == -1){
            System.out.println("Did not find employees with this specific id");
            return;
        }

        Employee employee = myEmployees[index];
        //create the new manager instance
        Developer developer = new Developer(employee.getEmpID(), employee.getEmpName(), employee.getEmpOfficeNo(), employee.getEmpOfficePhone(), languages);
        //update the old index to manager
        myEmployees[index] = developer;
    }

    public void assignManagerPosition(String empID) {
        int index = 0;

        index = findEmployeeById(empID);

        if(index == -1){
            System.out.println("Did not find employees with this specific id");
            return;
        }

        Employee employee = myEmployees[index];
        //create the new manager instance
        Manager manager = new Manager(employee.getEmpID(), employee.getEmpName(), employee.getEmpOfficeNo(), employee.getEmpOfficePhone());
        //update the old index to manager
        myEmployees[index] = manager;
    }

    public void assignEmpToProject (String empId, int projId){
        int index = 0;

        index = findEmployeeById(empId);

        if(index == -1){
            System.out.println("Did not find employees with this specific id");
            return;
        }

        Employee employee = myEmployees[index];

        Project p = getProjectById(projId);

        if(p == null){
            System.out.println("Did not find project with this specific id in assignEmpToProject");
            return;
        }

        employee.assignProject(p);
    }

    public void ceaseEmpFromProject (String empId, int projId){
        int index = 0;

        index = findEmployeeById(empId);

        if(index == -1){
            System.out.println("Did not find employees with this specific id");
            return;
        }

        Employee employee = myEmployees[index];

        //first check if there is a project with this id
        if(getProjectById(projId) == null){
            System.out.println("Did not find project with this specific id in ceaseEmpFromProject");
            return;
        }

        employee.ceaseFromProject(projId);
    }

    public String displayEmployees(){
        String sumToStirng = "";
        for(int i=0; i<myEmployees.length; i++){
            if(myEmployees[i] != null)
                sumToStirng += myEmployees[i].toString() + "\n";
        }
        return sumToStirng;
    }

    public String displayProjects() {
        String sumToStirng = "";
        for(int i=0; i<myProjects.length; i++){
            if(myProjects[i] != null)
                sumToStirng += myProjects[i].toString() + "\n";
        }
        return sumToStirng;
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
