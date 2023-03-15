package projectmanager.projectManagerAdmin;

import java.util.Arrays;
import java.util.Date;

import static utils.Globals.maxProjectsPerEmployee;

public class Employee {
    private String empID;
    private String empName;
    private String empOfficeNo;
    private String empOfficePhone;
    private Date HireDate;
    private Project [] workingOn;
    private int numOfProjects;

    public Employee(String empID, String empName) {
        this.empID = empID;
        this.empName = empName;
    }

    Employee(String id, String name, String empOfficeNo, String empOfficePhone){
        empID = id;
        empName = name;
        this.empOfficeNo = empOfficeNo;
        this.empOfficePhone = empOfficePhone;
        HireDate = new Date();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empID='" + empID + '\'' + "\t"+
                "empName='" + empName + '\'' + "\t"+
                "empOfficeNo='" + empOfficeNo + '\'' + "\t"+
                "empOfficePhone='" + empOfficePhone + '\'' + "\t"+
                "HireDate=" + HireDate + "\t"+
                "workingOn=" + Arrays.toString(workingOn) + "\t"+
                "numOfProjects=" + numOfProjects + "\t"+
                '}';
    }

    protected void assignProject(Project proj){
        //if there are no projects we occupy 5 new places
        if(workingOn == null)
            workingOn = new Project[maxProjectsPerEmployee];

        boolean added = false;
        for (int i = 0; i < workingOn.length && !added; i++) {
            if (workingOn[i] == null) {
                workingOn[i] = proj;
                added = true;
            }
        }

        if (!added) {
            System.out.println("No empty space available to add a new project.");
        }
    }

    protected void ceaseFromProject(int projIdx) {
        boolean removed = false;
        for(int i = 0; i<workingOn.length; i++){
            if(projIdx == workingOn[i].getProjectID()){
                workingOn[i] = null;
                removed = true;
                break;
            }
        }

        if (!removed) {
            System.out.println("No projects with this id found.");
        }
    }


    private void setEmpOfficeNo (String empOfficeNo, String pattern){
        //String pattern = "\\d{3}\\.[A-Z]\\d{2}\\.\\d";
        if(empOfficeNo.matches(pattern))
            this.empOfficeNo = empOfficeNo;
        else
            System.out.println("The Pattern does not match.");
    }

    private void setEmpOfficePhone(String empOfficePhone, String pattern){
        if(empOfficePhone.matches(pattern))
            this.empOfficePhone = empOfficePhone;
        else
            System.out.println("The Pattern does not match.");
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpOfficeNo() {
        return empOfficeNo;
    }

    public void setEmpOfficeNo(String empOfficeNo) {
        this.empOfficeNo = empOfficeNo;
    }

    public String getEmpOfficePhone() {
        return empOfficePhone;
    }

    public void setEmpOfficePhone(String empOfficePhone) {
        this.empOfficePhone = empOfficePhone;
    }

    public Date getHireDate() {
        return HireDate;
    }

    public void setHireDate(Date hireDate) {
        HireDate = hireDate;
    }

    public Project[] getWorkingOn() {
        return workingOn;
    }

    public void setWorkingOn(Project[] workingOn) {
        this.workingOn = workingOn;
    }

    public int getNumOfProjects() {
        return numOfProjects;
    }

    public void setNumOfProjects(int numOfProjects) {
        this.numOfProjects = numOfProjects;
    }
}
