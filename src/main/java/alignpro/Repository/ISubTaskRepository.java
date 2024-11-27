package alignpro.Repository;

import alignpro.Model.SubTask;

public interface ISubTaskRepository {

    void setConn();

    SubTask getSubTask(int subTaskID);

    void saveSubTask(String subTaskName, String startDate, String endDate,
                     int time, String subTaskDescription, String skillRequirement, int taskID);

    void deleteSubTask(int subTaskID);

    void editSubTask(SubTask subTask, int subTaskID);

}
