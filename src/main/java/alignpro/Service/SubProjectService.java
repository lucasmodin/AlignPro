package alignpro.Service;

import alignpro.Repository.ISubProjectRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SubProjectService {

    private final ISubProjectRepository subProjectRepository;

    public SubProjectService(ApplicationContext context, @Value("${subproject.repository.impl}") String impl) {
        subProjectRepository = (ISubProjectRepository) context.getBean(impl);
    }
}
