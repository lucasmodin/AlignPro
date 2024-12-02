package alignpro.Model.Projects;

import java.util.List;

public class TaskHierarchy {
    private Task task;
    private List<SubTask> subTasks;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }
}
