package alignpro.Controller.ProjectOverview;


import alignpro.Model.*;
import alignpro.Model.DTOModel.DashBoard_DTO;
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


    //TODO this is empty for now as I need to rewrite Logic
    @GetMapping("/pm-dashboard/{pmUserID}")
    public String getDashboard(@PathVariable("pmUserID") int pmUserID, Model model){
        DashBoard_DTO dashboard = alignProService.dataDashBoard(pmUserID);
        List<String> filterList = dashboard.filterList();
        model.addAttribute("data", dashboard);
        model.addAttribute("filterList", filterList);
        return "pm-Dashboard";
        //next is to implement logic for Task and subtask when the classes are made
    }

    @GetMapping("/pm-dashboard/filter")
    public String getfilterDashBoard(@RequestParam("filterProjects") String filterProjects, Model model){
        DashBoard_DTO unFilteredDashBoard = alignProService.dataDashBoard(1);
        unFilteredDashBoard.filter(filterProjects);
        model.addAttribute("data", unFilteredDashBoard);
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
