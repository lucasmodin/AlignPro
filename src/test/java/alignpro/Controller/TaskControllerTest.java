package alignpro.Controller;

import alignpro.Model.Task;
import alignpro.Service.ProjectService;
import alignpro.Service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)

class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void createTask() throws Exception{
        int subProjectID = 1;
        mockMvc.perform(get("/tasks/createTask/{subProjectID}", subProjectID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("create-Task"));
    }

    @Test
    void saveTask() throws Exception{
        Task task1 = new Task("Task 1", "2024-11-24", "2024-11-26", 2, "task task must do", "java", 1);
        mockMvc.perform(post("/tasks/saveTask")
                        .flashAttr("task1",task1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void deleteTask() throws Exception{
        int taskID = 1;

        mockMvc.perform(post("/tasks/deleteTask/{taskID}", taskID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void editTask() throws Exception{
        int taskID = 1;
        Task dummmyTask = new Task("Task 2", "2025-11-25", "2025-11-25", 5, "task task doooo", "java", 1);
        when(taskService.getTask(1)).thenReturn(dummmyTask);
        mockMvc.perform(get("/tasks/edit-task/{taskID}", taskID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("task", dummmyTask))
                .andExpect(view().name("edit-Task"));
    }

    @Test
    void updateTask() throws Exception{
        int taskID = 1;
        String taskName = "Task 2";
        String startDate = "2025-11-25";
        String endDate = "2025-11-25";
        int estimatedTime = 5;
        String taskDescription = "task task doooo";
        String skillRequirement = "java";

        mockMvc.perform(post("/tasks/updateTask")
                        .param("taskID", String.valueOf(taskID))
                        .param("taskName", taskName)
                        .param("startDate", startDate)
                        .param("endDate", endDate)
                        .param("estimatedTime", String.valueOf(estimatedTime))
                        .param("taskDescription", taskDescription)
                        .param("skillRequirement", skillRequirement))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));
    }
}