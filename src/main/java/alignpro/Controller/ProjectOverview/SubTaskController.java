package alignpro.Controller.ProjectOverview;

import alignpro.Model.Employee;
import alignpro.Model.Projects.SubTask;
import alignpro.Service.SubTaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                                @RequestParam("employeeList") List<Employee> employeeList,
                                Model model,
                                HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
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
        subTaskService.saveSubTask(newSubTask);

        if(employeeID != null) {
            subTaskService.assignEmployeeToTask(employeeID, pmUserID);
        }

        return "redirect:/pm-dashboard/" + pmUserID;
    }

    @GetMapping("/edit-subTask/{subTaskID}")
    public String editSubTask(@PathVariable("subTaskID") int subTaskID, Model model, HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        SubTask obj = subTaskService.getSubTask(subTaskID);
        if(obj != null){
            model.addAttribute("obj", obj);
            return "edit-SubTask";
        } else {
            return "redirect:/pm-dashboard/" + pmUserID;
        }
    }

    @PostMapping("/updateSubTask")
    public String updateSubTask(@RequestParam("subTaskID") int subTaskID,
                                @RequestParam("subTaskName") String subTaskName,
                                @RequestParam("subTaskDescription") String subTaskDescription,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate,
                                @RequestParam("time") int time,
                                @RequestParam("skillRequirement") String skillRequirement,
                                @RequestParam("employeeList") List<Employee> employeeList,
                                HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        int pmUserID = (int) session.getAttribute("pmUserID");
        SubTask subTask = new SubTask(subTaskID, subTaskName, subTaskDescription, startDate, endDate, time, skillRequirement);
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


}
