package alignpro.Model;

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
