package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ngiatrakos
 */
public class Globals {
    public static final String separator = "======================================================================================";
    
    /**************************** for Project 1*************************************/
    public static final int maxProjectsPerEmployee = 5;
    public static final int devBestLanguages = 3;  
    public static final int maxTasksPerProject = 5;
    public static final int maxEmployeesPerProject = 5;
    public static final int maxNumOfProjects = 10;
    public static final int maxNumOfEmployees = 20;

    public static final int maxProjectYears = 3;
    
    public static final String projectTimeunits = " Days ";
    
    public static final String projectReportHeader = "\t\t ProjectID \t ProjectName \t ProjectBudget \t\t ProjectDuration \t ProjectStatus\n";
    public static final String employeeReportHeader = "EmpID \t Name \t\t\t HireDate \tOffice \t\tPhone\n";
    public static final String developerReportHeader = employeeReportHeader + "\tLanguages\n";
    public static final String managerReportHeader = employeeReportHeader + "\tPromotedOn\n";
    public static final String taskReportHeader = "\t\t TaskID \t Title \t\t Start Date \t End Date \tDuration \tStatus\n";
    public static final String illegalDatesException = "Check the provided dates and try again.";
    
    public static final String officePattern = "^\\w{3}[.]\\w\\d{2}[.]{0,1}\\d{0,1}$";
    public static final String phonePattern = "^2821037\\d\\d\\d$";
    public static final String datePattern = "dd/MM/yyyy";
    
    public static final int projectPaddingRight = 10;
    public static final int employeePaddingRight = 20;
    
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
    
    public static enum programmingLanguages{
        PYTHON, 
        JAVA, 
        SCALA, 
        C, 
        CPP, 
        CSHARP, 
        RUBY
    }
    
    public static enum status{
        ONGOING,
        PENDING,
        COMPLETED
    }
    
    public static int computeDuration(Date from, Date to){
        long diffIn = Math.abs(to.getTime() - from.getTime());
        int duration = (int) TimeUnit.DAYS.convert(diffIn, TimeUnit.MILLISECONDS);
        return duration;
    }
    
    public static boolean validateDates(Date from, Date to){
        return from.before(to);
    }
    
    public static String pad(String text, int len, char value){
      if (text!=null){
        StringBuilder sb = new StringBuilder();
        if(text.length()<len){
            sb.append(text);
            for(int i=text.length();i<len;i++){
                sb.append(value);
            }
            return sb.toString();
        }
      }
      return text;
}
    
 
}
