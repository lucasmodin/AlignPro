package alignpro.Service;

import alignpro.Repository.ITaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ApplicationContext context, @Value("${task.repository.impl}") String impl) {
        taskRepository = (ITaskRepository) context.getBean(impl);
    }
}
