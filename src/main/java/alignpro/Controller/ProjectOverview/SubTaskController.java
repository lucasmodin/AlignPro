package alignpro.Controller.ProjectOverview;

import alignpro.Model.Employee;
import alignpro.Model.Projects.SubTask;
import alignpro.Service.SubTaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/subTasks")
public class SubTaskController {

    private final SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }

    @GetMapping("/createSubTask/{taskID}")
    public String createSubTask(@PathVariable("taskID") int taskID,
                                @RequestParam("employeeID") List<Integer> employeeIDs,
                                @RequestParam("employeeName") List<String> employeeNames,
                                Model model,
                                HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        List<Employee> employeeList = buildEmployeeList(employeeIDs, employeeNames);
        SubTask obj = new SubTask();
        obj.setTaskID(taskID);

        model.addAttribute("obj", obj);
        model.addAttribute("employeeList", employeeList);
        return "create-SubTask";
    }

    @PostMapping("/saveSubTask")
    public String saveSubTask(@ModelAttribute SubTask newSubTask,
                              @RequestParam(value = "employeeID", required = false) Integer employeeID,
                              HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        int subTaskID = subTaskService.saveSubTask(newSubTask);

        if(employeeID != null) {
            subTaskService.assignEmployeeToTask(subTaskID, employeeID);
        }

        return "redirect:/pm-dashboard/" + pmUserID;
    }

    @GetMapping("/edit-subTask/{subTaskID}")
    public String editSubTask(@PathVariable("subTaskID") int subTaskID,
                              @RequestParam("employeeID") List<Integer> employeeIDs,
                              @RequestParam("employeeName") List<String> employeeNames,
                              Model model,
                              HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        List<Employee> employeeList = buildEmployeeList(employeeIDs, employeeNames);

        SubTask obj = subTaskService.getSubTask(subTaskID);
        if(obj != null){
            model.addAttribute("obj", obj);
            model.addAttribute("employeeList", employeeList);
            return "edit-SubTask";
        } else {
            return "redirect:/pm-dashboard/" + pmUserID;
        }
    }

    @PostMapping("/updateSubTask")
    public String updateSubTask(@ModelAttribute SubTask newSubTask,
                                @RequestParam("employeeList") List<Employee> employeeList,
                                HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";

        int pmUserID = (int) session.getAttribute("pmUserID");

        SubTask subTask = new SubTask(newSubTask.getSubTaskID(), newSubTask.getSubTaskName(), newSubTask.getSubTaskDescription(),
                newSubTask.getStartDateString(), newSubTask.getEndDateString(), newSubTask.getTime(), newSubTask.getSkillRequirement());
        subTaskService.editSubTask(subTask, subTask.getSubTaskID());
        return "redirect:/pm-dashboard/" + pmUserID;
    }

    @PostMapping("/deleteSubTask/{subTaskID}")
    public String deleteSubTask(@PathVariable("subTaskID") int subTaskID, HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        subTaskService.deleteSubTask(subTaskID);
        return "redirect:/pm-dashboard/" + pmUserID;
    }

    public boolean isUserLoggedIn(HttpSession session){
        return session.getAttribute("pmUserID") == null;
    }

    //helper method - used more than one time in this controller.
    //the list is passed from pm-dashboard, but had trouble passing the whole list - had to loop through, and use hidden fields for ID and name, and build it again in here.
    private List<Employee> buildEmployeeList(List<Integer> employeeIDs, List<String> employeeNames) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < employeeIDs.size(); i++) {
            Employee employee = new Employee();
            employee.setEmployeeID(employeeIDs.get(i));
            employee.setEmployeeName(employeeNames.get(i));
            employeeList.add(employee);
        }
        return employeeList;
    }






}
