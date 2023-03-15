package projectmanager.projectManagerAdmin;

import java.util.Arrays;
import java.util.Date;

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

    private void assignProject(Project proj){

    }

    private void ceaseFromProject(int projIdx){

    }

    private void setEmpOfficeNo (String empOfficeNo, String pattern){

    }

    private void setEmpOfficePhone(String empOfficePhone, String pattern){

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
