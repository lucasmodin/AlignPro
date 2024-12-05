package alignpro.Service;

import alignpro.Model.Projects.SubTask;
import alignpro.Repository.Interfaces.ISubTaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SubTaskService {

    private final ISubTaskRepository subTaskRepository;

    public SubTaskService(ApplicationContext context, @Value("${subtask.repository.impl}") String impl) {
        subTaskRepository = (ISubTaskRepository) context.getBean(impl);
    }

    public void editSubTask(SubTask subTask, int subTaskID){
        subTaskRepository.editSubTask(subTask, subTaskID);
    }

    public int saveSubTask(SubTask subTask) {
        return subTaskRepository.saveSubTask(subTask.getSubTaskName(), subTask.getStartDateString(), subTask.getEndDateString(),
                subTask.getTime(), subTask.getSubTaskDescription(), subTask.getSkillRequirement(), subTask.getTaskID());
    }

    public SubTask getSubTask(int subTaskID){
        return subTaskRepository.getSubTask(subTaskID);
    }

    public void deleteSubTask(int subTaskID){
        subTaskRepository.deleteSubTask(subTaskID);
    }

    public void assignEmployeeToTask(int subTaskID, int employeeID){
        subTaskRepository.assignEmployeeToTask(subTaskID, employeeID);
    }
}
