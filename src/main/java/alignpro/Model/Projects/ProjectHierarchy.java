package alignpro.Model.Projects;

import java.util.List;

public class ProjectHierarchy {
    private Project project;
    private List<SubProjectHierarchy> subProjects;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<SubProjectHierarchy> getSubProjects() {
        return subProjects;
    }

    public void setSubProjects(List<SubProjectHierarchy> subProjects) {
        this.subProjects = subProjects;
    }
}
