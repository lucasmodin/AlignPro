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
        List<Employee> employeeList = subTaskService.buildEmployeeList(employeeIDs, employeeNames);
        SubTask obj = new SubTask();
        obj.setTaskID(taskID);

        model.addAttribute("obj", obj);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("pmUserID", session.getAttribute("pmUserID"));
        return "createHTML/create-SubTask";
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
        List<Employee> employeeList = subTaskService.buildEmployeeList(employeeIDs, employeeNames);

        SubTask obj = subTaskService.getSubTask(subTaskID);
        model.addAttribute("pmUserID", session.getAttribute("pmUserID"));
        if(obj != null){
            model.addAttribute("obj", obj);
            model.addAttribute("employeeList", employeeList);
            return "editHTML/edit-SubTask";
        } else {
            return "redirect:/pm-dashboard/" + pmUserID;
        }
    }

    @PostMapping("/updateSubTask")
    public String updateSubTask(@ModelAttribute SubTask newSubTask,
                                @RequestParam(value = "employeeID", required = false) Integer employeeID,
                                HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";

        int pmUserID = (int) session.getAttribute("pmUserID");

        SubTask subTask = new SubTask(newSubTask.getSubTaskID(), newSubTask.getSubTaskName(), newSubTask.getSubTaskDescription(),
                newSubTask.getStartDateString(), newSubTask.getEndDateString(), newSubTask.getTime(), newSubTask.getSkillRequirement());
        subTaskService.editSubTask(subTask, subTask.getSubTaskID());

        if (employeeID != null) {
            subTaskService.assignEmployeeToTask(subTask.getSubTaskID(), employeeID);
        }

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








}
