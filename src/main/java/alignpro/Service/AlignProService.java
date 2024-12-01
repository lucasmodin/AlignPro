package alignpro.Service;

import alignpro.Model.*;
import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;
import alignpro.Model.Projects.SubTask;
import alignpro.Model.Projects.Task;
import alignpro.Repository.Interfaces.IFAlignProRepository;
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

    //PM dashboard methods
    public List<Project> getAllProjects(int pmUserID){
        return alignProRepository.getProjectsForPMUser(pmUserID);
    }

    public List<SubProject> getAllSubProjects(int projectID){
        return alignProRepository.getSubProjectsForProject(projectID);
    }

    public List<Task> getAllTasks(int subProjectID){
        return alignProRepository.getTaskForSubProject(subProjectID);
    }

    public List<SubTask> getAllSubTasks(int taskID){
        return alignProRepository.getSubTaskForTask(taskID);
    }

    //*** methods to handle employees and skills ***//

    public Employee getEmployee(int employeeID){
        return alignProRepository.getEmployee(employeeID);
    }

    public void deleteEmployee(int employeeID){
        alignProRepository.deleteEmployee(employeeID);
    }

    public void editEmployee(Employee obj, int employeeID){
        alignProRepository.editEmployee(obj, employeeID);
    }


    public void saveEmployee(Employee obj){
        alignProRepository.saveEmployee(obj.getEmployeeName(),obj.getSkills());
    }

    public List<String> getListOfSkills(){
        return alignProRepository.getListOfSkills();
    }

    public List<Employee> getListOfEmployees(){
        return alignProRepository.getListOfEmployees();
    }







}
