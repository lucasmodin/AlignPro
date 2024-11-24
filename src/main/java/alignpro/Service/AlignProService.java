package alignpro.Service;

import alignpro.Model.Project;
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
        System.out.println("dos it go into Service Layer");
        alignProRepository.saveProject(obj.getProjectName(), obj.getStartDateString(), obj.getDeadlineString(), obj.getProjectDescription());
    }

}
