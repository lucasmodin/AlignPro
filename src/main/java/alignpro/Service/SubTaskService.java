package alignpro.Service;

import alignpro.Repository.ISubTaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SubTaskService {

    private final ISubTaskRepository subTaskRepository;

    public SubTaskService(ApplicationContext context, @Value("${subtask.repository.impl}") String impl) {
        subTaskRepository = (ISubTaskRepository) context.getBean(impl);
    }
}
