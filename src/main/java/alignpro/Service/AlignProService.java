package alignpro.Service;

import alignpro.Model.*;
import alignpro.Model.DTOModel.*;
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
import java.util.Map;


@Service
public class AlignProService {

    private final IFAlignProRepository alignProRepository;

    public AlignProService (ApplicationContext context, @Value("${AlignPro.repository.impl}") String impl){
        alignProRepository = (IFAlignProRepository) context.getBean(impl);
    }


    //PM dashboard data transfer

    public DashBoard_DTO dataDashBoard(int pmUserID){
        DashBoard_DTO dataTransferObj = new DashBoard_DTO();

        List<Project> projects = alignProRepository.getProjectsForPMUser(pmUserID);
        List<SubProject> subProjects = alignProRepository.getSubProjectsForProject();
        List<Task> task = alignProRepository.getTaskForSubProject();
        List<SubTask> subTask = alignProRepository.getSubTaskForTask();


        Map<String,String> stuffUnderPM = new HashMap<>();

        List<Integer> pmProjects = alignProRepository.pmUserProjectID(pmUserID);
        for(int pmProjectsInt : pmProjects){
            stuffUnderPM.putAll(alignProRepository.projectNamesToSubprojectandTask(pmProjectsInt));
        }


        List<ProjectDTO> projectDTOSList = new ArrayList<>();
        for (Project pjoObj : projects){
                ProjectDTO projectDTO = new ProjectDTO();

                projectDTO.setFilter(stuffUnderPM.get(pjoObj.getProjectName()));
                projectDTO.setProjectID(pjoObj.getProjectID());
                projectDTO.setProjectName(pjoObj.getProjectName());
                projectDTO.setProjectDescription(pjoObj.getProjectDescription());
                projectDTO.setStartDate(pjoObj.getStartDate());
                projectDTO.setDeadLine(pjoObj.getDeadLine());
                projectDTO.setTotalTime(pjoObj.getTotalTime());

                projectDTOSList.add(projectDTO);

            }


        List<SubProjectDTO> subProjectDTOSList = new ArrayList<>();
        for (SubProject subProjectObj : subProjects){
            if(stuffUnderPM.get(subProjectObj.getSubProjectName()) != null){
                SubProjectDTO subProjectDTO = new SubProjectDTO();

                subProjectDTO.setFilter(stuffUnderPM.get(subProjectObj.getSubProjectName()));
                subProjectDTO.setSubProjectID(subProjectObj.getSubProjectID());

                subProjectDTO.setSubProjectName(subProjectObj.getSubProjectName());

                subProjectDTO.setSubProjectDescription(subProjectObj.getSubProjectDescription());
                subProjectDTO.setStartDate(subProjectObj.getStartDate());
                subProjectDTO.setEndDate(subProjectObj.getEndDate());
                subProjectDTO.setSumTime(subProjectObj.getSumTime());
                subProjectDTO.setFkProjectID(subProjectObj.getFkProjectID());

                subProjectDTOSList.add(subProjectDTO);

            }
        }

        List<TaskDTO> taskDTOSList = new ArrayList<>();
        for (Task taskObj : task){
            if(stuffUnderPM.get(taskObj.getTaskName()) != null){
                TaskDTO taskDTO = new TaskDTO();

                taskDTO.setFilter(stuffUnderPM.get(taskObj.getTaskName()));
                taskDTO.setTaskID(taskObj.getTaskID());

                taskDTO.setTaskName(taskObj.getTaskName());

                taskDTO.setTaskDescription(taskObj.getTaskDescription());
                taskDTO.setStartDate(taskObj.getStartDate());
                taskDTO.setEndDate(taskObj.getEndDate());
                taskDTO.setEstimatedTime(taskObj.getEstimatedTime());
                taskDTO.setSubProjectID(taskObj.getSubProjectID());

                taskDTOSList.add(taskDTO);
            }
        }

        List<SubTaskDTO> subTaskDTOSList = new ArrayList<>();
        for (SubTask subTaskObj : subTask){
            if(stuffUnderPM.get(subTaskObj.getSubTaskName()) != null){
                SubTaskDTO subTaskDTO = new SubTaskDTO();

                subTaskDTO.setFilter(stuffUnderPM.get(subTaskObj.getSubTaskName()));
                subTaskDTO.setSubTaskID(subTaskObj.getSubTaskID());

                subTaskDTO.setSubTaskName(subTaskObj.getSubTaskName());

                subTaskDTO.setSubTaskDescription(subTaskObj.getSubTaskDescription());
                subTaskDTO.setStartDate(subTaskObj.getStartDate());
                subTaskDTO.setEndDate(subTaskObj.getEndDate());
                subTaskDTO.setTime(subTaskObj.getTime());
                subTaskDTO.setTaskID(subTaskObj.getTaskID());

                subTaskDTOSList.add(subTaskDTO);
            }
        }

        dataTransferObj.setProjectList(projectDTOSList);
        dataTransferObj.setSubProjectList(subProjectDTOSList);
        dataTransferObj.setTaskList(taskDTOSList);
        dataTransferObj.setSubTaskList(subTaskDTOSList);

        return dataTransferObj;
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
