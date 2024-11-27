package alignpro.Controller;

import alignpro.Model.Task;
import alignpro.Service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/edit-task/{taskID}")
    public String editTask(@PathVariable("taskID") int taskID, Model model){
        Task task = taskService.getTask(taskID);
        if(task != null){
            model.addAttribute("task", task);
            return "edit-Task";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updateTask")
    public String updateTask(@RequestParam("taskID") int taskID,
                             @RequestParam("taskName") String taskName,
                             @RequestParam("startDate") String startDate,
                             @RequestParam("endDate") String endDate,
                             @RequestParam("estimatedTime") int estimatedTime,
                             @RequestParam("taskDescription") String taskDescription,
                             @RequestParam("skillRequirement") String skillRequirement){
        Task task = new Task(taskID, taskName, startDate, endDate,
                estimatedTime, taskDescription, skillRequirement);
        taskService.editTask(task, task.getTaskID());
        return "redirect:/";
    }

    @GetMapping("/createTask/{subProjectID}")
    public String createTask(@PathVariable("subProjectID") int subProjectID, Model model){
        Task obj = new Task();
        obj.setSubProjectID(subProjectID);
        model.addAttribute("obj", obj);
        return "create-Task";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute Task newTask){
        taskService.saveTask(newTask);
        return "redirect:/";
    }

    @PostMapping("/deleteTask/{taskID}")
    public String deleteTask(@PathVariable("taskID") int taskID){
        taskService.deleteTask(taskID);
        return "redirect:/";
    }
}
