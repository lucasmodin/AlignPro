package alignpro.Service;

import alignpro.Model.Projects.Task;
import alignpro.Repository.Interfaces.ITaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ApplicationContext context, @Value("${task.repository.impl}") String impl) {
        taskRepository = (ITaskRepository) context.getBean(impl);
    }

    public void saveTask(Task obj){
        taskRepository.saveTask(obj.getTaskName(), obj.getStartDateString(), obj.getEndDateString(),
                obj.getEstimatedTime(), obj.getTaskDescription(), obj.getSkillRequirement(), obj.getSubProjectID());
    }

    public void editTask(Task task, int taskID){
        try{
            taskRepository.editTask(task, taskID);
        }catch (Exception e){
            throw new RuntimeException("error updating task" + taskID, e);
        }
    }

    public Task getTask(int taskID){
        return taskRepository.getTask(taskID);
    }

    public void deleteTask(int taskID){
        taskRepository.deleteTask(taskID);
    }
}
