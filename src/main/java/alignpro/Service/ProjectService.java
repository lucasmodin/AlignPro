package alignpro.Service;

import alignpro.Model.Project;
import alignpro.Repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final IProjectRepository projectRepository;

    public ProjectService(ApplicationContext context, @Value("${project.repository.impl}") String impl){
        projectRepository = (IProjectRepository) context.getBean(impl);
    }


    public void saveProject(Project obj){
        projectRepository.saveProject(obj.getProjectName(), obj.getStartDateString(),
                obj.getDeadlineString(), obj.getProjectDescription());
    }

    public void editProject(Project project, int projectID){
        try {
            projectRepository.editProject(project, projectID);
        } catch (Exception e) {
            throw new RuntimeException("error updating project " + projectID, e);
        }
    }

    public Project getProject(int projectID){
        return projectRepository.getProject(projectID);
    }

    public Project getProject(String projectName){
        return projectRepository.getProject(projectName);
    }

    public void deleteProject(int projectID){
        projectRepository.deleteProject(projectID);
    }
}
