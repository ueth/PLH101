/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.projectmanager.projectΜanagerFrontEnd;

import java.text.ParseException;
import project1.projectmanager.projectManagerAdmin.IMS;
import project1.utils.Globals;
import project1.utils.Globals.programmingLanguages;



/**
 *
 * @author ngiatrakos
 */
public class ProjectManagerCLI {
    
    /** The main method should just create objects and call methods
     *  The  main method contains just the application logic 
     *  It should not implement any additional functionality.
     */
    public static void main (String [] args) throws ParseException{//parse exception for parsing dates
        IMS IS = new IMS(Globals.maxNumOfProjects, Globals.maxNumOfEmployees);
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
         //insert some Empoyees
         //using one constructor
        IS.insertEmployee("323", Globals.pad("Nektarios Gioldasis",Globals.employeePaddingRight,' '), null, null); 
        //using the other constructor
        IS.insertEmployee("324", Globals.pad("Nikos Giatrakos",Globals.employeePaddingRight,' '), "147.A35.1", "2821037265"); 
        // try to insert an employee with the same ID to check that it is not accepted
        IS.insertEmployee("324", Globals.pad("Ioannis Maragkoudakis",Globals.employeePaddingRight,' '), null, null);
        // if we change the employee id to a new one, it should get inserted
        IS.insertEmployee("325", Globals.pad("Ioannis Maragkoudakis",Globals.employeePaddingRight,' '), null, null);
        IS.insertEmployee("326", Globals.pad("Stefanos Karasavvidis",Globals.employeePaddingRight,' '), "141.A12.3", "2821037266");
        // if we try to display all employees again there should be no duplicate ID
        System.out.println(IS.displayEmployees());
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        //update info of employees that had empty office number and phone
        IS.updateEmployee("323", null, "141.B65.1", "2821037696");//carefull! leaving the name null means it won't be updated, not set to null!
        IS.updateEmployee("325", null, "141.B65.3", "2821037888");//carefull! leaving the name null means it won't be updated, not set to null! 
        // check that employees were successfully updated
        System.out.println(IS.displayEmployees());
        
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        //assign positions to employees. Initially we will set 3/4 to being developers
        IS.assignDevPosition("323", new programmingLanguages[]{programmingLanguages.PYTHON, 
                                    programmingLanguages.JAVA, programmingLanguages.C});
        IS.assignDevPosition("324", new programmingLanguages[]{programmingLanguages.JAVA, 
                                    programmingLanguages.PYTHON, programmingLanguages.SCALA});
        IS.assignDevPosition("325", new programmingLanguages[]{programmingLanguages.JAVA, 
                                    programmingLanguages.C, programmingLanguages.CPP});
        // check employees were successfully updated
        //can you tell which "toString()" method was called? the one in the Employee or the Developer class?
        System.out.println(IS.displayEmployees());
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        // then one of the employees becomes a manager
        IS.assignManagerPosition("326");
        // check employees were successfully updated
        System.out.println(IS.displayEmployees());
        
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        //insert some projects
        IS.insertProject(Globals.pad("EVENFLOW",Globals.projectPaddingRight,' '), 500000.054);
        IS.insertProject(Globals.pad("INFORE2",Globals.projectPaddingRight,' '), 1800000.58);
        IS.insertProject(Globals.pad("AcronAI",Globals.projectPaddingRight,' '), 800000.00);
        System.out.println(IS.displayProjects());
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        IS.updateProject(0, null, 490000); //carefull leaving the title null means it won't be updated, not set to null!
        System.out.println(IS.displayProjects());
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        //insert tasks to project
        IS.insertProjectTask(IS.findProjectByName(Globals.pad("EVENFLOW",Globals.projectPaddingRight,' ')), 
                                "Neural Learning Algorithms", 
                                Globals.dateFormatter.parse("01/10/2022") , Globals.dateFormatter.parse("01/03/2023"), null);        
        //now we insert a task that chronically precedes the above one. So that if the task Id of the above
        //was 0.0, it should automatically turn to 0.1 if we insert the new task as shown below
        IS.insertProjectTask(IS.findProjectByName(Globals.pad("EVENFLOW",Globals.projectPaddingRight,' ')), 
                                "Dynamic Maintenance of Neural Models", 
                                Globals.dateFormatter.parse("01/09/2022"), Globals.dateFormatter.parse("29/09/2022"), null);
        //now we insert a task that should be appended at the end of this project's task list
        IS.insertProjectTask(IS.findProjectByName(Globals.pad("EVENFLOW",Globals.projectPaddingRight,' ')), 
                                "Formal Verification Models", 
                                Globals.dateFormatter.parse("01/04/2023"), Globals.dateFormatter.parse("29/09/2023"), null);
        IS.insertProjectTask(IS.findProjectByName(Globals.pad("EVENFLOW",Globals.projectPaddingRight,' ')), 
                                "Neuro-Symbolic Learning", 
                                Globals.dateFormatter.parse("02/10/2022"), Globals.dateFormatter.parse("29/03/2023"), null);
        //check that the task durations, task statuses are ok and the task ids have been configured automatically and correctly
        System.out.println(IS.getProjectByName(Globals.pad("EVENFLOW",Globals.projectPaddingRight,' ')).displayTasks());
        //check that the duration of the project has been automatically computed correctly
        
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        //update the info of one task
        //whatever we leave null means that we do not want it to get updated
        IS.updateProjectTask("0.2", null, null, null, Globals.status.COMPLETED);
        IS.updateProjectTask("0.1", null,  Globals.dateFormatter.parse("23/03/2023"),  Globals.dateFormatter.parse("01/11/2023"), null);
        System.out.println(IS.getProjectByName(Globals.pad("EVENFLOW",Globals.projectPaddingRight,' ')).displayTasks());
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        //assining project to employee
        IS.assignEmpToProject("324", IS.findProjectById(0));
        IS.assignEmpToProject("324", IS.findProjectById(1));
        IS.assignEmpToProject("323", IS.findProjectByName(Globals.pad("AcronAI",Globals.projectPaddingRight,' ')));
        IS.assignEmpToProject("323", IS.findProjectByName(Globals.pad("INFORE2",Globals.projectPaddingRight,' ')));
        IS.assignEmpToProject("325", IS.findProjectByName(Globals.pad("INFORE2",Globals.projectPaddingRight,' ')));
        IS.assignEmpToProject("325", IS.findProjectById(2));
        IS.assignEmpToProject("325", IS.findProjectByName(Globals.pad("INFORE2",Globals.projectPaddingRight,' ')));
        System.out.println(IS.displayEmployees());
        
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        IS.assignEmpToProject("323", IS.findProjectByName(Globals.pad("INFORE2",Globals.projectPaddingRight,' ')));
        IS.insertProjectTask(IS.findProjectByName(Globals.pad("INFORE2",Globals.projectPaddingRight,' ')), 
                                "Optimization Algorithms", 
                                Globals.dateFormatter.parse("01/04/2023"), Globals.dateFormatter.parse("29/09/2023"), null);
        IS.updateProjectTask("1.0", null, null, null, Globals.status.COMPLETED);
        IS.updateProject(IS.findProjectByName(Globals.pad("INFORE2",Globals.projectPaddingRight,' ')), 
                            Globals.pad("CREXDATA",Globals.projectPaddingRight,' '), 0);
        //Now check that not only you inserted and updated myProjects array and the tasks etc but these were
        //(should be) also updated in the workingOn array of each Project object of each employee!!! This is because 
        //the references are on the same objects! OOP rocks!
        System.out.println(IS.displayEmployees());
        System.out.println(IS.getProjectByName(Globals.pad("CREXDATA",Globals.projectPaddingRight,' ')).displayTasks());
        
        /* the fat lady sings*/
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        System.out.println(IS.displayProjects());
        //do some deletes here
        IS.deleteEmployee("326");
        IS.deleteProject(IS.getProjectByName(Globals.pad("AcronAI",Globals.projectPaddingRight,' ')).getProjectID());
        IS.deleteTask("0.0");
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        System.out.println(IS.getProjectById(0).displayTasks());
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        System.out.println(IS.displayProjects());
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        System.out.println(IS.displayEmployees());
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
        IS.ceaseEmpFromProject("324", 0);
        System.out.println(IS.displayEmployees());
        System.out.println(Globals.separator);
        System.out.println(Globals.separator);
    
    }
}
