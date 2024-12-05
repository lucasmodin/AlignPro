package alignpro.Controller;

import alignpro.Controller.ProjectOverview.SubTaskController;
import alignpro.Model.Employee;
import alignpro.Model.Projects.SubTask;
import alignpro.Service.SubTaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(SubTaskController.class)

class SubTaskControllerTest {

    private final int pmUserID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubTaskService subTaskService;


    //note - after we added an employeeID list and employee name to parameters, we need to convert then to arrays (can be empty), as the mockMvc .param doesnt accept lists
    @Test
    void editSubTask() throws Exception{
        List<String> employeeIDs = List.of("1", "2", "3");
        List<String> employeeNames = List.of("a", "b", "c");
        int subTaskID = 1;
        SubTask dummyProject = new SubTask(1, "SubTask 2", "task task doooo", "2025-11-25", "2025-11-25", 5, "java");
        when(subTaskService.getSubTask(1)).thenReturn(dummyProject);
        mockMvc.perform(get("/subTasks/edit-subTask/{subTaskID}", subTaskID)
                        .sessionAttr("pmUserID", pmUserID)
                        .param("employeeID", employeeIDs.toArray(new String[0]))
                        .param("employeeName", employeeNames.toArray(new String[0])))
                .andExpect(status().isOk())
                .andExpect(model().attribute("obj", dummyProject))
                .andExpect(view().name("editHTML/edit-SubTask"));
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
        Employee employee = new Employee(1,"john");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);


        mockMvc.perform(post("/subTasks/updateSubTask").sessionAttr("pmUserID", pmUserID)
                        .param("subTaskID", String.valueOf(subTaskID))
                        .param("subTaskName", subTaskName)
                        .param("subTaskDescription", subTaskDescription)
                        .param("startDate", startDate)
                        .param("endDate", endDate)
                        .param("time", String.valueOf(time))
                        .param("skillRequirement", skillRequirement)
                        .param("employeeList", String.valueOf(employeeList) ))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pm-dashboard/" + pmUserID))
                .andExpect(view().name("redirect:/pm-dashboard/" + pmUserID));
    }

    @Test
    void createSubTask() throws Exception{
        List<String> employeeIDs = List.of("1", "2", "3");
        List<String> employeeNames = List.of("a", "b", "c");
        int taskID = 1;
        mockMvc.perform(get("/subTasks/createSubTask/{taskID}", taskID)
                        .param("employeeID", employeeIDs.toArray(new String[0]))
                        .param("employeeName", employeeNames.toArray(new String[0]))
                        .sessionAttr("pmUserID", pmUserID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("createHTML/create-SubTask"));
    }

    @Test
    void saveSubTask() throws Exception{
        SubTask subTask = new SubTask();
        mockMvc.perform(post("/subTasks/saveSubTask", subTask).sessionAttr("pmUserID", pmUserID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pm-dashboard/" + pmUserID))
                .andExpect(view().name("redirect:/pm-dashboard/" + pmUserID));
    }

    @Test
    void deleteSubTask() throws Exception{
        int subTaskID = 1;
        mockMvc.perform(post("/subTasks/deleteSubTask/{subTaskID}", subTaskID).sessionAttr("pmUserID", pmUserID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pm-dashboard/" + pmUserID));
    }

}