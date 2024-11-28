package alignpro.Model;


import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;

//wrapper DTO - is more tightly coupled to the model than the more flatter DTO structure, but is more simple
public class PMDashboardRow {
    private Project project;
    private SubProject subProject;
    //TODO add Task and Subtask when implemented

    public PMDashboardRow() {

    }

    public PMDashboardRow(Project project, SubProject subProject) {
        this.project = project;
        this.subProject = subProject;
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
}
