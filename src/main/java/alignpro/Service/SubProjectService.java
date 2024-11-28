package alignpro.Service;

import alignpro.Model.Projects.SubProject;
import alignpro.Repository.Interfaces.ISubProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SubProjectService {

    private final ISubProjectRepository subProjectRepository;

    public SubProjectService(ApplicationContext context, @Value("${subproject.repository.impl}") String impl) {
        subProjectRepository = (ISubProjectRepository) context.getBean(impl);
    }

    public void saveSubProject(SubProject obj){
        subProjectRepository.saveSubProject(obj.getSubProjectName(), obj.getStartDateString(),
                obj.getEndDateString(), obj.getSubProjectDescription(), obj.getFkProjectID());
    }

    public void editSubProject(SubProject subProject, int subProjectID){
        try {
            subProjectRepository.editSubProject(subProject, subProjectID);
        }catch (Exception e){
            throw new RuntimeException("error updating subproject " + subProjectID, e);
        }
    }

    public SubProject getSubProject(String subProjectName){
        return subProjectRepository.getSubProject(subProjectName);
    }

    public SubProject getSubProject(int subProjectID){
        return subProjectRepository.getSubProject(subProjectID);
    }

    public void deleteSubProject(int subProjectID){
        subProjectRepository.deleteSubProject(subProjectID);
    }
}
