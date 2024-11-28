package alignpro.Controller;

import alignpro.Model.SubTask;
import alignpro.Service.ProjectService;
import alignpro.Service.SubTaskService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(SubTaskController.class)

class SubTaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubTaskService subTaskService;

    @Test
    void editSubTask() throws Exception{
        int subTaskID = 1;
        SubTask dummyProject = new SubTask(1, "SubTask 2", "task task doooo", "2025-11-25", "2025-11-25", 5, "java");
        when(subTaskService.getSubTask(1)).thenReturn(dummyProject);
        mockMvc.perform(get("/subTasks/edit-subTask/{subTaskID}", subTaskID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("obj", dummyProject))
                .andExpect(view().name("edit-SubTask"));
    }

    @Test
    void updateSubTask() throws Exception{
        int subTaskID = 1;
        String subTaskName = "SubTask 2";
        String subTaskDescription = "task task doooo";
        String startDate = "2025-11-25";
        String endDate = "2025-11-25";
        int time = 5;
        String skillRequirement = "java";

        mockMvc.perform(post("/subTasks/updateSubTask")
                        .param("subTaskID", String.valueOf(subTaskID))
                        .param("subTaskName", subTaskName)
                        .param("subTaskDescription", subTaskDescription)
                        .param("startDate", startDate)
                        .param("endDate", endDate)
                        .param("time", String.valueOf(time))
                        .param("skillRequirement", skillRequirement))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));
    }

    @Test
    void createSubTask() throws Exception{
        int taskID = 1;
        mockMvc.perform(get("/subTasks/createSubTask/{taskID}", taskID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("create-SubTask"));
    }

    @Test
    void saveSubTask() throws Exception{
        SubTask subTask = new SubTask();
        mockMvc.perform(post("/subTasks/saveSubTask", subTask))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));
    }

    @Test
    void deleteSubTask() throws Exception{
        int subTaskID = 1;
        mockMvc.perform(post("/subTasks/deleteSubTask/{subTaskID}", subTaskID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

}