package alignpro.Model;


import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;
import alignpro.Model.Projects.SubTask;
import alignpro.Model.Projects.Task;

//wrapper DTO - is more tightly coupled to the model than the more flatter DTO structure, but is more simple
public class PMDashboardRow {
    private Project project;
    private SubProject subProject;
    private Task task;
    private SubTask subTask;
    //TODO add Task and Subtask when implemented

    public PMDashboardRow() {

    }

    public PMDashboardRow(Project project, SubProject subProject, Task task, SubTask subTask) {
        this.project = project;
        this.subProject = subProject;
        this.task = task;
        this.subTask = subTask;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public SubProject getSubProject() {
        return subProject;
    }

    public void setSubProject(SubProject subProject) {
        this.subProject = subProject;
    }

    public Task getTask() {
        return task;
    }

    public SubTask getSubTask() {
        return subTask;
    }
}
