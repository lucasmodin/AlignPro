package alignpro.Model.Projects;

import java.util.List;

public class SubProjectHierarchy {
    private SubProject subProject;
    private List<TaskHierarchy> tasks;

    public SubProject getSubProject() {
        return subProject;
    }

    public void setSubProject(SubProject subProject) {
        this.subProject = subProject;
    }

    public List<TaskHierarchy> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskHierarchy> tasks) {
        this.tasks = tasks;
    }
}
