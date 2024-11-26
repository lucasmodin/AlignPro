package alignpro.Controller;


import alignpro.Model.Employee;
import alignpro.Model.Project;
import alignpro.Service.AlignProService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

}
