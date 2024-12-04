package alignpro.Controller.ProjectOverview;

import alignpro.Model.Projects.SubTask;
import alignpro.Service.SubTaskService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subTasks")
public class SubTaskController {

    private final SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }

    @GetMapping("/createSubTask/{taskID}")
    public String createSubTask(@PathVariable("taskID") int taskID, Model model, HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        SubTask obj = new SubTask();
        obj.setTaskID(taskID);
        model.addAttribute("obj", obj);
        return "create-SubTask";
    }

    @PostMapping("/saveSubTask")
    public String saveSubTask(@ModelAttribute SubTask newSubTask, HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        subTaskService.saveSubTask(newSubTask);
        return "redirect:/";
    }

    @GetMapping("/edit-subTask/{subTaskID}")
    public String editSubTask(@PathVariable("subTaskID") int subTaskID, Model model, HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        SubTask obj = subTaskService.getSubTask(subTaskID);
        if(obj != null){
            model.addAttribute("obj", obj);
            return "edit-SubTask";
        } else {
            return "redirect:/";
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
                                HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        SubTask subTask = new SubTask(subTaskID, subTaskName, subTaskDescription, startDate, endDate, time, skillRequirement);
        subTaskService.editSubTask(subTask, subTask.getSubTaskID());
        return "redirect:/";
    }

    @PostMapping("/deleteSubTask/{subTaskID}")
    public String deleteSubTask(@PathVariable("subTaskID") int subTaskID, HttpSession session) {
        if (isUserLoggedIn(session)) return "redirect:/login";
        subTaskService.deleteSubTask(subTaskID);
        return "redirect:/";
    }

    public boolean isUserLoggedIn(HttpSession session){
        return session.getAttribute("pmUserID") == null;
    }


}
