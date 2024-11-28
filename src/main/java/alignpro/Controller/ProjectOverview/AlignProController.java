package alignpro.Controller.ProjectOverview;


import alignpro.Model.*;
import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;
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

}
