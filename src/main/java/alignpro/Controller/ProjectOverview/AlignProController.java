package alignpro.Controller.ProjectOverview;


import alignpro.Model.*;
import alignpro.Model.DTOModel.DashBoard_DTO;
import alignpro.Service.AlignProService;
import jakarta.servlet.http.HttpSession;
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
    public String createEmployee(Model model, HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        Employee employee = new Employee();
        List<String> listOfSkills = alignProService.getListOfSkills();
        model.addAttribute("employeeObj", employee);
        model.addAttribute("listOfSkills", listOfSkills);
        model.addAttribute("pmUserID", session.getAttribute("pmUserID"));
        return "createHTML/create-Employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee newEmployee, HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        alignProService.saveEmployee(newEmployee);
        return "redirect:/pm-dashboard/" + session.getAttribute("pmUserID");
    }

    @PostMapping("/deleteEmployee/{employeeID}")
    public String deleteEmployee(@PathVariable("employeeID") int employeeID, HttpSession session){

        Employee employee = alignProService.getEmployee(employeeID);
        if(employee != null && !isUserLoggedIn(session)) {
            alignProService.deleteEmployee(employeeID);
            return "redirect:/pm-dashboard/" + session.getAttribute("pmUserID");
        } else {
            return "redirect:/";
        }
    }


    //TODO this is empty for now as I need to rewrite Logic
    @GetMapping("/pm-dashboard/{pmUserID}")
    public String getDashboard(@PathVariable("pmUserID") int pmUserID, Model model, HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        DashBoard_DTO dashboard = alignProService.dataDashBoard(pmUserID);
        List<String> filterList = dashboard.filterList();
        List<Employee> employeeList = alignProService.getListOfEmployees();
        model.addAttribute("data", dashboard);
        model.addAttribute("filterList", filterList);
        model.addAttribute("employeeList", employeeList);
        return "pm-Dashboard";
        //next is to implement logic for Task and subtask when the classes are made
    }


    //TODO it needs to get Project Manager PrimaryKey from Session to be able to get correct data.
    @GetMapping("/pm-dashboard/filter")
    public String getfilterDashBoard(@RequestParam("filterProjects") String filterProjects, Model model, HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        DashBoard_DTO unFilteredDashBoard = alignProService.dataDashBoard(1);
        unFilteredDashBoard.filter(filterProjects);
        model.addAttribute("data", unFilteredDashBoard);
        return "pm-Dashboard";
    }

    @GetMapping("/{ID}/editEmployee")
    public String editEmployee(@PathVariable int ID, Model model, HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        Employee objToUpdate = alignProService.getEmployee(ID);
        model.addAttribute("EmployeeObj", objToUpdate);
        model.addAttribute("Skills", alignProService.getListOfSkills());
        model.addAttribute("pmUserID", session.getAttribute("pmUserID"));
        return "editHTML/edit-employee";
    }

    @PostMapping("updateEmployee")
    public String updateEmployee(
            @RequestParam("employeeID") int employeeID,
            @RequestParam("employeeName") String employeeName,
            @RequestParam("skills") List<String> skills,
            HttpSession session){
        if (isUserLoggedIn(session)) return "redirect:/login";
        Employee objUpdate = new Employee(employeeName, skills);
        alignProService.editEmployee(objUpdate,employeeID);
        return "redirect:/pm-dashboard/" + session.getAttribute("pmUserID");
    }

    @GetMapping("/sustainability")
    public String showSustainabilityPage(HttpSession session, Model model) {
        Integer pm = (Integer) session.getAttribute("pmUserID");
        boolean isLoggedIn = (pm != null);
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "sustainability";
    }

    public boolean isUserLoggedIn(HttpSession session){
        return session.getAttribute("pmUserID") == null;
    }


}
