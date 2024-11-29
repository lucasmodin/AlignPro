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
        List<ProjectDTO> dashboardData = new ArrayList<>();


        List<Project> projects = alignProService.getAllProjects(pmUserID);

        for (Project project : projects) {

            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setProjectName(project.getProjectName());
            projectDTO.setStartDate(project.getStartDateString());
            projectDTO.setDeadline(project.getDeadlineString());
            projectDTO.setTotalSumTime(project.getTotalTime());


            List<SubProject> subProjects = alignProService.getAllSubProjects(project.getProjectID());
            List<SubProjectDTO> subProjectDTOs = new ArrayList<>();

            for (SubProject subProject : subProjects) {

                SubProjectDTO subProjectDTO = new SubProjectDTO();
                subProjectDTO.setSubProjectName(subProject.getSubProjectName());
                subProjectDTO.setStartDate(subProject.getStartDateString());
                subProjectDTO.setEndDate(subProject.getEndDateString());


                List<Task> tasks = alignProService.getAllTasks(subProject.getSubProjectID());
                List<TaskDTO> taskDTOs = new ArrayList<>();

                for (Task task : tasks) {

                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setTaskName(task.getTaskName());
                    taskDTO.setStartDate(task.getStartDateString());
                    taskDTO.setEndDate(task.getEndDateString());

                    List<SubTask> subTasks = alignProService.getAllSubTasks(task.getTaskID());
                    List<SubTaskDTO> subTaskDTOs = new ArrayList<>();

                    for (SubTask subTask : subTasks) {

                        SubTaskDTO subTaskDTO = new SubTaskDTO();
                        subTaskDTO.setSubTaskName(subTask.getSubTaskName());
                        subTaskDTO.setStartDate(subTask.getStartDateString());
                        subTaskDTO.setEndDate(subTask.getEndDateString());
                        subTaskDTO.setEstimatedTime(subTask.getTime());


                        subTaskDTOs.add(subTaskDTO);
                    }


                    taskDTO.setSubtasks(subTaskDTOs);

                    taskDTOs.add(taskDTO);
                }

                subProjectDTO.setTasks(taskDTOs);

                subProjectDTOs.add(subProjectDTO);
            }

            projectDTO.setSubProjects(subProjectDTOs);

            dashboardData.add(projectDTO);
        }

        model.addAttribute("dashboardData", dashboardData);

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
