package alignpro.Repository.Interfaces;

import alignpro.Model.Projects.Task;

public interface ITaskRepository {

    void setConn();

    Task getTask(int taskID);

    void editTask(Task task, int taskID);

    void deleteTask(int taskID);

    void saveTask(String taskName, String startDate, String endDate, int estimatedTime,
                  String taskDescription, String skillRequirement, int subProjectID);

}
