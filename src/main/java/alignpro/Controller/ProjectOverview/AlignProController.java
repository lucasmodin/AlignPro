package alignpro.Controller.ProjectOverview;


import alignpro.DTO.ProjectDTO;
import alignpro.DTO.SubProjectDTO;
import alignpro.DTO.SubTaskDTO;
import alignpro.DTO.TaskDTO;
import alignpro.Model.*;
import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;
import alignpro.Model.Projects.SubTask;
import alignpro.Model.Projects.Task;
import alignpro.Service.AlignProService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class AlignProController {

    private final AlignProService alignProService;

    public AlignProController(AlignProService service){
        this.alignProService = service;
    }

    @GetMapping("/CreateEmployee")
    public String createEmployee(Model model){
        Employee employee = new Employee();
        List<String> listOfSkills = alignProService.getListOfSkills();
        model.addAttribute("employeeObj", employee);
        model.addAttribute("listOfSkills", listOfSkills);
        return "create-Employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee newEmployee){
        alignProService.saveEmployee(newEmployee);
        return "redirect:/";
    }

    @PostMapping("/deleteEmployee/{employeeID}")
    public String deleteEmployee(@PathVariable("employeeID") int employeeID){
        Employee employee = alignProService.getEmployee(employeeID);
        if(employee != null) {
            alignProService.deleteEmployee(employeeID);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }


    //TODO fix endpoint when user/login session is implemented
    @GetMapping("/pm-dashboard/{pmUserID}")
    public String getDashboard(@PathVariable("pmUserID") int pmUserID, Model model){
        // Initialize the list to hold the final DTOs
        List<ProjectDTO> dashboardData = new ArrayList<>();

        // Fetch projects for the user
        List<Project> projects = alignProService.getAllProjects(pmUserID);

        for (Project project : projects) {
            // Create and populate a ProjectDTO
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setProjectName(project.getProjectName());
            projectDTO.setStartDate(project.getStartDate());
            projectDTO.setDeadline(project.getDeadLine());
            projectDTO.setTotalSumTime(project.getTotalTime());

            // Fetch subprojects for the current project
            List<SubProject> subProjects = alignProService.getAllSubProjects(project.getProjectID());
            List<SubProjectDTO> subProjectDTOs = new ArrayList<>();

            for (SubProject subProject : subProjects) {
                // Create and populate a SubProjectDTO
                SubProjectDTO subProjectDTO = new SubProjectDTO();
                subProjectDTO.setSubProjectName(subProject.getSubProjectName());
                subProjectDTO.setStartDate(subProject.getStartDate());
                subProjectDTO.setEndDate(subProject.getEndDate());

                // Fetch tasks for the current subproject
                List<Task> tasks = alignProService.getAllTasks(subProject.getSubProjectID());
                List<TaskDTO> taskDTOs = new ArrayList<>();

                for (Task task : tasks) {
                    // Create and populate a TaskDTO
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setTaskName(task.getTaskName());
                    taskDTO.setStartDate(task.getStartDateString());
                    taskDTO.setEndDate(task.getEndDateString());

                    // Fetch subtasks for the current task
                    List<SubTask> subTasks = alignProService.getAllSubTasks(task.getTaskID());
                    List<SubTaskDTO> subTaskDTOs = new ArrayList<>();

                    for (SubTask subTask : subTasks) {
                        // Create and populate a SubTaskDTO
                        SubTaskDTO subTaskDTO = new SubTaskDTO();
                        subTaskDTO.setSubTaskName(subTask.getSubTaskName());
                        subTaskDTO.setStartDate(subTask.getStartDate());
                        subTaskDTO.setEndDate(subTask.getEndDate());
                        subTaskDTO.setEstimatedTime(subTask.getTime());

                        // Add the SubTaskDTO to the list
                        subTaskDTOs.add(subTaskDTO);
                    }

                    // Add subtasks to the TaskDTO
                    taskDTO.setSubtasks(subTaskDTOs);

                    // Add the TaskDTO to the task list
                    taskDTOs.add(taskDTO);
                }

                // Add tasks to the SubProjectDTO
                subProjectDTO.setTasks(taskDTOs);

                // Add the SubProjectDTO to the subproject list
                subProjectDTOs.add(subProjectDTO);
            }

            // Add subprojects to the ProjectDTO
            projectDTO.setSubProjects(subProjectDTOs);

            // Add the ProjectDTO to the dashboard data
            dashboardData.add(projectDTO);
        }

        // Add the dashboard data to the model
        model.addAttribute("dashboardData", dashboardData);

        // Return the view name
        return "pm-Dashboard";
    }


    @GetMapping("/{ID}/editEmployee")
    public String editEmployee(@PathVariable int ID, Model model){
        Employee objToUpdate = alignProService.getEmployee(ID);
        model.addAttribute("EmployeeObj", objToUpdate);
        model.addAttribute("Skills", alignProService.getListOfSkills());
        return "/edit-employee";
    }

    @PostMapping("updateEmployee")
    public String updateEmployee(
            @RequestParam("employeeID") int employeeID,
            @RequestParam("employeeName") String employeeName,
            @RequestParam("skills") List<String> skills){
        Employee objUpdate = new Employee(employeeName, skills);
        alignProService.editEmployee(objUpdate,employeeID);
        return "redirect:/";
    }



}
