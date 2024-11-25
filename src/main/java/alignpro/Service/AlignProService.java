package alignpro.Service;

import alignpro.Model.Project;
import alignpro.Model.SubProject;
import alignpro.Repository.IFAlignProRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class AlignProService {

    private final IFAlignProRepository alignProRepository;

    public AlignProService (ApplicationContext context, @Value("${AlignPro.repository.impl}") String impl){
        alignProRepository = (IFAlignProRepository) context.getBean(impl);
    }

    public void saveProject(Project obj){
        alignProRepository.saveProject(obj.getProjectName(), obj.getStartDateString(), obj.getDeadlineString(), obj.getProjectDescription());
    }

    public void saveSubProject(SubProject obj){
        alignProRepository.saveSubProject(obj.getSubProjectName(), obj.getStartDateString(),
                obj.getEndDateString(), obj.getSubProjectDescription(), obj.getFkProjectID());
    }

    public void editProject(Project project, int projectID){
        try {
            alignProRepository.editProject(project, projectID);
        } catch (Exception e) {
            throw new RuntimeException("error updating project " + projectID, e);
        }
    }



















    public Project getProject(int projectID){
        return alignProRepository.getProject(projectID);
    }

    public Project getProject(String projectName){
        return alignProRepository.getProject(projectName);
    }

}
