package projectmanager.projectManagerAdmin;

import utils.Globals;

import java.util.Date;

public class Task {
    private String taskID, taskTitle;
    private Globals.status taskStatus;
    private Date taskFromDate;
    private Date taskEndDate;
    private int taskDuration;

    Task(String id, String title, Date start, Date end, Globals.status stat){

    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID='" + taskID + '\'' + "\t"+
                "taskTitle='" + taskTitle + '\'' + "\t"+
                "taskStatus=" + taskStatus + "\t"+
                "taskFromDate=" + taskFromDate + "\t"+
                "taskEndDate=" + taskEndDate + "\t"+
                "taskDuration=" + taskDuration + "\t"+
                '}';
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Globals.status getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Globals.status taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getTaskFromDate() {
        return taskFromDate;
    }

    public void setTaskFromDate(Date taskFromDate) {
        this.taskFromDate = taskFromDate;
    }

    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(int taskDuration) {
        this.taskDuration = taskDuration;
    }
}
