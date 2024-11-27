package alignpro.Controller;

import alignpro.Model.SubProject;
import alignpro.Service.SubProjectService;
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
    public String createSubProject(@PathVariable("projectID") int projectID, Model model){
        SubProject obj = new SubProject();
        obj.setFkProjectID(projectID);
        model.addAttribute("obj", obj);
        return "create-SubProject";
    }

    @PostMapping("/saveSubProject")
    public String saveSubProject(@ModelAttribute SubProject newSubProject){
        subProjectService.saveSubProject(newSubProject);
        return "redirect:/";
    }

    @GetMapping("/edit-subproject/{subProjectID}")
    public String editSubProject(@PathVariable("subProjectID") int subProjectID, Model model){
        SubProject subProject = subProjectService.getSubProject(subProjectID);
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
        subProjectService.editSubProject(subProject, subProject.getSubProjectID());

        return "redirect:/";
    }

    @PostMapping("/deleteSubProject/{subProjectID}")
    public String deleteSubProject(@PathVariable("subProjectID") int subProjectID){
        subProjectService.deleteSubProject(subProjectID);
        return "redirect:/";
    }

}
