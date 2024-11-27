package alignpro.Service;

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
}
