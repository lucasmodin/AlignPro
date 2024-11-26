package alignpro.Controller;


import alignpro.Model.PMDashboardRow;
import alignpro.Model.Employee;
import alignpro.Model.Project;
import alignpro.Model.SubProject;
import alignpro.Service.AlignProService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


import java.util.List;

@Controller
@RequestMapping("")
public class AlignProController {

    private final AlignProService alignProService;

    public AlignProController(AlignProService service){
        this.alignProService = service;
    }


    @GetMapping("/CreateProject")
    public String createProject(Model model){
        Project obj = new Project();
        model.addAttribute("obj", obj);
        return "create-Project";
    }

    @PostMapping("/saveProject")
    public String saveProject(@ModelAttribute Project newProject){
        alignProService.saveProject(newProject);
        return "redirect:/";
    }

    @GetMapping("/edit-subproject/{subProjectID}")
    public String editSubProject(@PathVariable("subProjectID") int subProjectID, Model model){
        SubProject subProject = alignProService.getSubProject(subProjectID);
        if(subProject != null){
            model.addAttribute("subProject", subProject);
            return "edit-SubProject";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updateSubProject")
    public String updateSubProject(@RequestParam("subProjectID") int subProjectID,
                                   @RequestParam("subProjectName") String subProjectName,
                                   @RequestParam("startDate") String startDate,
                                   @RequestParam("endDate") String endDate,
                                   @RequestParam("subProjectDescription") String subProjectDescription/*,
                                   @RequestParam("fkProjectID") int fkProjectID*/){
        SubProject subProject = new SubProject(subProjectID, subProjectName, startDate
                , endDate, subProjectDescription);
        alignProService.editSubProject(subProject, subProject.getSubProjectID());

        return "redirect:/";
    }

    @GetMapping("/edit-project/{projectId}")
    public String editProject(@PathVariable ("projectId") int projectId, Model model) {
        Project project = alignProService.getProject(projectId);
        if (project != null) {
            model.addAttribute("project", project);
            return "edit-project";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updateProject")
    public String updateProject(@RequestParam("projectId") int projectId,
                                @RequestParam("projectName") String projectName,
                                @RequestParam("projectDescription") String projectDescription,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("deadLine") String deadLine) {
        Project project = new Project(projectId, projectName, startDate, deadLine, projectDescription);
        alignProService.editProject(project, project.getProjectID());
        return "redirect:/";
    }

    @PostMapping("/delete-project/{projectID}")
    public String deleteProject(@PathVariable("projectID") int projectID){
        Project projectToDelete = alignProService.getProject(projectID);
        if(projectToDelete != null){
            alignProService.deleteProject(projectID);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
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
    @GetMapping("/createSubProject/{projectID}")
    public String createSubProject(@PathVariable("projectID") int projectID, Model model){
        SubProject obj = new SubProject();
        obj.setFkProjectID(projectID);
        model.addAttribute("obj", obj);
        return "create-SubProject";
    }

    @PostMapping("/saveSubProject")
    public String saveSubProject(@ModelAttribute SubProject newSubProject){
        alignProService.saveSubProject(newSubProject);
        return "redirect:/";
    }

    //TODO fix endpoint when user/login session is implemented
    @GetMapping("/pm-dashboard/{pmUserID}")
    public String getDashboard(@PathVariable("pmUserID") int pmUserID, Model model){
        List<PMDashboardRow> dashboardRows = new ArrayList<>();
        List<Project> projects = alignProService.getAllProjects(pmUserID);

        for (Project project : projects) {
            List<SubProject> subProjects = alignProService.getAllSubProjects(project.getProjectID());

            if (subProjects.isEmpty()) {
                dashboardRows.add(new PMDashboardRow(project, null));
            } else {
                for (SubProject subProject : subProjects) {
                    dashboardRows.add(new PMDashboardRow(project, subProject));
                }
            }

        }

        model.addAttribute("dashboardRows", dashboardRows);
        return "pm-Dashboard";
        //next is to implement logic for Task and subtask when the classes are made
    }



    @PostMapping("/deleteSubProject/{subProjectID}")
    public String deleteSubProject(@PathVariable("subProjectID") int subProjectID){
        alignProService.deleteSubProject(subProjectID);
        return "redirect:/";
    }
}
