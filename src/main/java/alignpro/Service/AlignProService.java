package alignpro.Service;

import alignpro.Model.*;
import alignpro.Model.DTOModel.DashBoard_DTO;
import alignpro.Model.DTOModel.ProjectDTO;
import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;
import alignpro.Model.Projects.SubTask;
import alignpro.Model.Projects.Task;
import alignpro.Repository.Interfaces.IFAlignProRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AlignProService {

    private final IFAlignProRepository alignProRepository;

    public AlignProService (ApplicationContext context, @Value("${AlignPro.repository.impl}") String impl){
        alignProRepository = (IFAlignProRepository) context.getBean(impl);
    }


    public DashBoard_DTO standardDashBoardView (){

        DashBoard_DTO dashBoard = new DashBoard_DTO();
        dashBoard.

        return
    }


    // Helper Methods to DashBoard convert from object to DTO object//

    public List<ProjectDTO> convertProject(int pmUerID){
        List<ProjectDTO> obj = new ArrayList<>();


        List<Project> allProjects = getAllProjects(pmUerID);

        if(allProjects != null){
            for(Project objProject: allProjects){
                ProjectDTO dtoObj = new ProjectDTO();

                dtoObj.setFilter(objProject.getProjectName());
                dtoObj.setProjectName(objProject.getProjectName());
                dtoObj.setProjectDescription(objProject.getProjectDescription());
                dtoObj.setStartDate(objProject.getStartDate());
                dtoObj.setDeadLine(objProject.getDeadLine());

                obj.add(dtoObj);
            }
        } else {
            return obj;
        }

        return obj;
    }

    public List<ProjectDTO> convertSubProject(int pmUerID){
        List<ProjectDTO> obj = new ArrayList<>();


        List<Project> allProjects = getAllProjects(pmUerID);

        if(allProjects != null){
            for(Project objProject: allProjects){
                ProjectDTO dtoObj = new ProjectDTO();

                dtoObj.setFilter(objProject.getProjectName());
                dtoObj.setProjectName(objProject.getProjectName());
                dtoObj.setProjectDescription(objProject.getProjectDescription());
                dtoObj.setStartDate(objProject.getStartDate());
                dtoObj.setDeadLine(objProject.getDeadLine());

                obj.add(dtoObj);
            }
        } else {
            return obj;
        }

        return obj;
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

    //*** List of Skills and Employees ***//
    public List<String> getListOfSkills(){
        return alignProRepository.getListOfSkills();
    }

    public List<Employee> getListOfEmployees(){
        return alignProRepository.getListOfEmployees();
    }







}
