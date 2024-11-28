package alignpro.Controller.ProjectOverview;

import alignpro.Model.Projects.Project;
import alignpro.Service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @GetMapping("/CreateProject")
    public String createProject(Model model){
        Project obj = new Project();
        model.addAttribute("obj", obj);
        return "create-Project";
    }

    @PostMapping("/saveProject")
    public String saveProject(@ModelAttribute Project newProject){
        projectService.saveProject(newProject);
        return "redirect:/";
    }

    @GetMapping("/edit-project/{projectId}")
    public String editProject(@PathVariable("projectId") int projectId, Model model) {
        Project project = projectService.getProject(projectId);
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
        projectService.editProject(project, project.getProjectID());
        return "redirect:/";
    }

    @PostMapping("/delete-project/{projectID}")
    public String deleteProject(@PathVariable("projectID") int projectID){
        Project projectToDelete = projectService.getProject(projectID);
        if(projectToDelete != null){
            projectService.deleteProject(projectID);
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }



}
