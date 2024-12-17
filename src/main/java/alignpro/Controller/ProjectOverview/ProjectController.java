package alignpro.Controller.ProjectOverview;

import alignpro.Model.Projects.Project;
import alignpro.Service.ProjectService;
import jakarta.servlet.http.HttpSession;
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
    @ModelAttribute
    public void addAttributes(HttpSession session, Model model){
        Integer pm = (Integer) session.getAttribute("pmUserID");
        boolean isLoggedIn = (pm != null);
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("pmUserID", pm);
    }

    @GetMapping("/CreateProject")
    public String createProject(Model model, HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        Project obj = new Project();
        model.addAttribute("obj", obj);
        model.addAttribute("pmUserID", pmUserID);
        return "createHTML/create-Project";
    }

    @PostMapping("/saveProject")
    public String saveProject(@ModelAttribute Project newProject,
                              @RequestParam("pmUserID") int pmUserID,
                              HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        projectService.saveProject(newProject, pmUserID);
        return "redirect:/pm-dashboard/" + pmUserID ;
    }

    @GetMapping("/edit-project/{projectId}")
    public String editProject(@PathVariable("projectId") int projectId, Model model, HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        Project project = projectService.getProject(projectId);
        int pmUserID = (int) session.getAttribute("pmUserID");
        if (project != null) {
            model.addAttribute("project", project);
            model.addAttribute("pmUserID", pmUserID);
            return "editHTML/edit-project";
        } else {
            return "redirect:/pm-dashboard/" + pmUserID;
        }
    }

    @PostMapping("/updateProject")
    public String updateProject(@RequestParam("projectId") int projectId,
                                @RequestParam("projectName") String projectName,
                                @RequestParam("projectDescription") String projectDescription,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("deadLine") String deadLine,
                                HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        Project project = new Project(projectId, projectName, startDate, deadLine, projectDescription);
        projectService.editProject(project, project.getProjectID());
        return "redirect:/pm-dashboard/" + pmUserID;
    }

    @PostMapping("/delete-project/{projectID}")
    public String deleteProject(@PathVariable("projectID") int projectID, HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        Project projectToDelete = projectService.getProject(projectID);
        int pmUserID = (int) session.getAttribute("pmUserID");
        if(projectToDelete != null){
            projectService.deleteProject(projectID);
            return "redirect:/pm-dashboard/" + pmUserID ;
        } else {
            return "redirect:/";
        }
    }

    public boolean isUserLoggedIn(HttpSession session){
        return session.getAttribute("pmUserID") == null;
    }



}
