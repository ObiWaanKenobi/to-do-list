package by.gsu.epamlab.model.beans;

import by.gsu.epamlab.controller.constants.Constants;

import java.sql.Date;

public class Task {
    private int taskId;
    private String taskName;
    private Date taskDate;
    private boolean isFixed;
    private boolean isBinned;
    private String fileName;
    private String shortFileName;

    public Task() {
    }

    public Task(int taskId, String taskName, Date taskDate, boolean isFixed, boolean isBinned, String fileName) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.isFixed = isFixed;
        this.isBinned = isBinned;
        this.fileName = fileName;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public boolean isBinned() {
        return isBinned;
    }

    public void setBinned(boolean binned) {
        isBinned = binned;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getShortFileName() {
        return fileName.substring(fileName.indexOf(Constants.FILE_NAME_DELIMITER) + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (taskId != task.taskId) return false;
        if (isFixed != task.isFixed) return false;
        if (isBinned != task.isBinned) return false;
        if (taskName != null ? !taskName.equals(task.taskName) : task.taskName != null) return false;
        if (taskDate != null ? !taskDate.equals(task.taskDate) : task.taskDate != null) return false;
        return fileName != null ? fileName.equals(task.fileName) : task.fileName == null;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (taskDate != null ? taskDate.hashCode() : 0);
        result = 31 * result + (isFixed ? 1 : 0);
        result = 31 * result + (isBinned ? 1 : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String formattedFileName = (fileName == null)? null : ("\'" + fileName + "\'");
        return "{" +
                "taskId : " + taskId +
                ", taskName : '" + taskName + '\'' +
                ", taskDate : '" + taskDate + '\'' +
                ", isFixed : " + isFixed +
                ", isBinned : " + isBinned +
                ", fileName : " + formattedFileName  +
                '}';
    }


}
