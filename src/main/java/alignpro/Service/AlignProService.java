package alignpro.Service;

import alignpro.Model.*;
import alignpro.Repository.IFAlignProRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlignProService {

    private final IFAlignProRepository alignProRepository;

    public AlignProService (ApplicationContext context, @Value("${AlignPro.repository.impl}") String impl){
        alignProRepository = (IFAlignProRepository) context.getBean(impl);
    }

    //*** methods to handle Projects ***//
    public void saveProject(Project obj){
        alignProRepository.saveProject(obj.getProjectName(), obj.getStartDateString(),
                obj.getDeadlineString(), obj.getProjectDescription());
    }

    public void saveSubProject(SubProject obj){
        alignProRepository.saveSubProject(obj.getSubProjectName(), obj.getStartDateString(),
                obj.getEndDateString(), obj.getSubProjectDescription(), obj.getFkProjectID());
    }

    public void saveTask(Task obj){
        alignProRepository.saveTask(obj.getTaskName(), obj.getStartDateString(), obj.getEndDateString(),
                obj.getEstimatedTime(), obj.getTaskDescription(), obj.getSkillRequirement(), obj.getSubProjectID());
    }

    public void editProject(Project project, int projectID){
        try {
            alignProRepository.editProject(project, projectID);
        } catch (Exception e) {
            throw new RuntimeException("error updating project " + projectID, e);
        }
    }

    public List<Project> getAllProjects(int pmUserID){
        return alignProRepository.getProjectsForPMUser(pmUserID);
    }

    public List<SubProject> getAllSubProjects(int projectID){
        return alignProRepository.getSubProjectsForProject(projectID);
    }

    public List<Task> getAllTasks(int subProjectID){
        return alignProRepository.getTaskForSubProject(subProjectID);
    }



















    public void editSubProject(SubProject subProject, int subProjectID){
        try {
            alignProRepository.editSubProject(subProject, subProjectID);
        }catch (Exception e){
            throw new RuntimeException("error updating subproject " + subProjectID, e);
        }
    }

    public void editTask(Task task, int taskID){
        try{
            alignProRepository.editTask(task, taskID);
        }catch (Exception e){
            throw new RuntimeException("error updating task" + taskID, e);
        }
    }

    public void editSubTask(SubTask subTask, int subTaskID){
        alignProRepository.editSubTask(subTask, subTaskID);
    }

    public void saveSubTask(SubTask subTask) {
        alignProRepository.saveSubTask(subTask.getSubTaskName(), subTask.getStartDateString(), subTask.getEndDateString(),
                subTask.getTime(), subTask.getSubTaskDescription(), subTask.getSkillRequirement(), subTask.getTaskID());
    }












    public void deleteSubProject(int subProjectID){
        alignProRepository.deleteSubProject(subProjectID);
    }

    public void deleteTask(int taskID){
        alignProRepository.deleteTask(taskID);
    }

    public Project getProject(int projectID){
        return alignProRepository.getProject(projectID);
    }

    public Project getProject(String projectName){
        return alignProRepository.getProject(projectName);
    }

    public void deleteProject(int projectID){
        alignProRepository.deleteProject(projectID);
    }

    //*** methods to handle employees and skills ***//
    public void saveEmployee(Employee obj){
        alignProRepository.saveEmployee(obj.getEmployeeName(),obj.getSkills());
    }

    public List<String> getListOfSkills(){
        return alignProRepository.getListOfSkills();
    }

    public List<Employee> getListOfEmployees(){
        return alignProRepository.getListOfEmployees();
    }

    public SubProject getSubProject(String subProjectName){
        return alignProRepository.getSubProject(subProjectName);
    }

    public SubProject getSubProject(int subProjectID){
        return alignProRepository.getSubProject(subProjectID);
    }

    public Task getTask(int taskID){
        return alignProRepository.getTask(taskID);
    }

    public SubTask getSubTask(int subTaskID){
        return alignProRepository.getSubTask(subTaskID);
    }

    public void deleteSubTask(int subTaskID){
        alignProRepository.deleteSubTask(subTaskID);
    }

}
