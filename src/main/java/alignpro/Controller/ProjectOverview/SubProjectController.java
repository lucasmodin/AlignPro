package alignpro.Controller.ProjectOverview;

import alignpro.Model.Projects.SubProject;
import alignpro.Service.SubProjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subProjects")
public class SubProjectController {

    private final SubProjectService subProjectService;

    public SubProjectController(SubProjectService subProjectService) {
        this.subProjectService = subProjectService;
    }

    @GetMapping("/createSubProject/{projectID}")
    public String createSubProject(@PathVariable("projectID") int projectID, Model model, HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        SubProject obj = new SubProject();
        obj.setFkProjectID(projectID);
        model.addAttribute("obj", obj);
        return "create-SubProject";
    }

    @PostMapping("/saveSubProject")
    public String saveSubProject(@ModelAttribute SubProject newSubProject, HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        subProjectService.saveSubProject(newSubProject);
        return "redirect:/pm-dashboard/" + pmUserID;
    }

    @GetMapping("/edit-subproject/{subProjectID}")
    public String editSubProject(@PathVariable("subProjectID") int subProjectID, Model model, HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        SubProject subProject = subProjectService.getSubProject(subProjectID);
        if(subProject != null){
            model.addAttribute("subProject", subProject);
            return "edit-SubProject";
        } else {
            return "redirect:/pm-dashboard/" + pmUserID;
        }
    }

    @PostMapping("/updateSubProject")
    public String updateSubProject(@RequestParam("subProjectID") int subProjectID,
                                   @RequestParam("subProjectName") String subProjectName,
                                   @RequestParam("startDate") String startDate,
                                   @RequestParam("endDate") String endDate,
                                   @RequestParam("subProjectDescription") String subProjectDescription,/*,
                                   @RequestParam("fkProjectID") int fkProjectID*/
                                    HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        SubProject subProject = new SubProject(subProjectID, subProjectName, startDate
                , endDate, subProjectDescription);
        subProjectService.editSubProject(subProject, subProject.getSubProjectID());

        return "redirect:/pm-dashboard/" + pmUserID;
    }

    @PostMapping("/deleteSubProject/{subProjectID}")
    public String deleteSubProject(@PathVariable("subProjectID") int subProjectID, HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        subProjectService.deleteSubProject(subProjectID);
        return "redirect:/pm-dashboard/" + pmUserID;
    }

    public boolean isUserLoggedIn(HttpSession session){
        return session.getAttribute("pmUserID") == null;
    }

}
