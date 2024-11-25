package alignpro.Controller;


import alignpro.Model.PMDashboardRow;
import alignpro.Model.Project;
import alignpro.Model.SubProject;
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

    @GetMapping("/createSubProject")
    public String createSubProject(Model model){
        SubProject obj = new SubProject();
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
            List<SubProject> subprojects = alignProService.getAllSubProjects(project.getProjectID());
            if(subprojects.isEmpty()){
                dashboardRows.add(new PMDashboardRow(
                        project.getProjectName(),
                        project.getStartDateString(),
                        project.getDeadlineString(),
                        project.getProjectDescription(),
                        project.getTotalTime(),
                        null, null, null, null, 0,
                        null, null, null, null, 0,
                        null, null, null, null, 0
                        ));
            }
            model.addAttribute(dashboardRows);
            return "pm-Dashboard";
        }


        //next is to implement logic for Task and subtask when the classes are made
    }







}
