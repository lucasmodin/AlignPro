package alignpro.Service;

import alignpro.Model.*;
import alignpro.Model.DTOModel.DashBoard_DTO;
import alignpro.Model.DTOModel.ProjectDTO;
import alignpro.Model.DTOModel.SubProjectDTO;
import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;
import alignpro.Model.Projects.SubTask;
import alignpro.Model.Projects.Task;
import alignpro.Repository.Interfaces.IFAlignProRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    //*** List of Skills and Employees ***//
    public List<String> getListOfSkills(){
        return alignProRepository.getListOfSkills();
    }

    public List<Employee> getListOfEmployees(){
        return alignProRepository.getListOfEmployees();
    }



    //TODO this is old methods, did an redesign.
    /*

    public DashBoard_DTO pmDashBoardView (int pmUserID){

        DashBoard_DTO dashBoard = new DashBoard_DTO();
        dashBoard.setProjectList(convertProject(pmUserID));
        dashBoard.setSubProjectList(convertSubProject(pmUserID));


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

    public List<SubProjectDTO> convertSubProject(int pmUerID){

        List<SubProjectDTO> obj = new ArrayList<>();


        List<Integer> projectIDs = pmSubProjectID(pmUerID);

    if (projectIDs != null){
        for(Integer ids : projectIDs){
            for (SubProject subProObj : getAllSubProjects(ids)){
                SubProjectDTO dtoObj = new SubProjectDTO();

                dtoObj.setFilter(getAllProjects(pmUerID).get(ids).getProjectName());

                dtoObj.setSubProjectName(subProObj.getSubProjectName());
                dtoObj.setSubProjectDescription(subProObj.getSubProjectDescription());
                dtoObj.setStartDate(subProObj.getStartDate());
                dtoObj.setEndDate(subProObj.getEndDate());
                dtoObj.setSumTime(subProObj.getSumTime());

                obj.add(dtoObj);
            }
        }

    } else {
        return obj;
    }

    return obj;
    }


    // Helper method connect PMUser to Project ID
    public List<Integer> pmSubProjectID(int pmUserID){
        return alignProRepository.pmUserProjectID(pmUserID);
    }

    public List<Integer> subProjectIDtoTask(int pmUserID){
        List<Integer> subProjectsConnectedToProjectID = pmSubProjectID(pmUserID);
        List<Integer>
        for ()

    }


     */




}
