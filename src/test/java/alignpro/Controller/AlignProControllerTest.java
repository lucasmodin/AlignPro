package alignpro.Controller;

import alignpro.Model.*;
import alignpro.Service.AlignProService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlignProController.class)


public class AlignProControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlignProService service;
    @Autowired
    private AlignProService alignProService;


    @Test
    void testCreateNewProject() throws Exception {
        mockMvc.perform(get("/CreateProject"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("create-Project"));
    }

    @Test
    void saveNewProject() throws Exception {
        Project project1 = new Project("New Project", "2024-11-24", "2024-11-26","Hehe det er en joke");
        mockMvc.perform(post("/saveProject")
                        .flashAttr("projectObj",project1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testCreateNewSubProject() throws Exception {
        int projectID = 1;
        mockMvc.perform(get("/createSubProject/{projectID}", projectID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("create-SubProject"));
    }

    @Test
    void saveNewSubProject() throws Exception {
        SubProject subProject = new SubProject("make crud functions", "2024-11-25", "2024-11-26", "make all crud now!");

        mockMvc.perform(post("/saveSubProject").
                        flashAttr("subProject", subProject))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void createTask() throws Exception{
        int subProjectID = 1;
        mockMvc.perform(get("/createTask/{subProjectID}", subProjectID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("create-Task"));
    }

    @Test
    void saveTask() throws Exception{
        Task task1 = new Task("Task 1", "2024-11-24", "2024-11-26", 2, "task task must do", "java", 1);
        mockMvc.perform(post("/saveTask")
                        .flashAttr("task1",task1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void deleteTask() throws Exception{
        int taskID = 1;

        mockMvc.perform(post("/deleteTask/{taskID}", taskID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void editTask() throws Exception{
        int taskID = 1;
        Task dummmyTask = new Task("Task 2", "2025-11-25", "2025-11-25", 5, "task task doooo", "java", 1);
        when(alignProService.getTask(1)).thenReturn(dummmyTask);
        mockMvc.perform(get("/edit-task/{taskID}", taskID))
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

        mockMvc.perform(post("/updateTask")
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

    @Test
    void deleteSubProject() throws Exception{
        int subProjectId = 1;

        mockMvc.perform(post("/deleteSubProject/{subProjectID}", subProjectId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void editProject() throws Exception {
        int projectId = 1;
        Project dummyProject = new Project(1,"dummy", "2024-11-11", "2024-11-22", "dummy project");
        when(alignProService.getProject(1)).thenReturn(dummyProject);
        mockMvc.perform(get("/edit-project/{projectId}", projectId))
                .andExpect(status().isOk())
                .andExpect(model().attribute("project", dummyProject))
                .andExpect(view().name("edit-project"));
        }

    @Test
    void editSubTask() throws Exception{
        int subTaskID = 1;
        SubTask dummyProject = new SubTask(1, "SubTask 2", "task task doooo", "2025-11-25", "2025-11-25", 5, "java");
        when(alignProService.getSubTask(1)).thenReturn(dummyProject);
        mockMvc.perform(get("/edit-subTask/{subTaskID}", subTaskID))
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

        mockMvc.perform(post("/updateSubTask")
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
    void editSubProject() throws Exception{
        int subProjectID = 1;
        SubProject dummySubProject = new SubProject(1,"dummy", "2024-11-11", "2024-11-22", "dummy project");
        when(alignProService.getSubProject(1)).thenReturn(dummySubProject);
        mockMvc.perform(get("/edit-subproject/{subProjectID}", subProjectID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("subProject", dummySubProject))
                .andExpect(view().name("edit-SubProject"));
    }

    @Test
    void updateSubProject() throws Exception{
        int subProjectID = 1;
        String subProjectName = "dummy project";
        String startDate = "2024-11-11";
        String endDate = "2024-11-22";
        String subProjectDescription = "dummy project";

        mockMvc.perform(post("/updateSubProject")
                        .param("subProjectID", String.valueOf(subProjectID))
                        .param("subProjectName",subProjectName)
                        .param("startDate", startDate)
                        .param("endDate", endDate)
                        .param("subProjectDescription",subProjectDescription))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));
    }

    //TODO
    //when we build more functionality and a dashboard for the PM, we need to change the redirects
    @Test
    void updateProject() throws Exception {
        int projectId = 1;
        String projectName = "dummy project";
        String startDate = "2024-11-11";
        String deadline = "2024-11-22";
        String projectDescription = "dummy project";

        mockMvc.perform(post("/updateProject")
                .param("projectId", String.valueOf(projectId))
                .param("projectName",projectName)
                .param("startDate", startDate)
                .param("deadLine", deadline)
                .param("projectDescription",projectDescription))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));
        }
    @Test
    void testCreateNewEmployee() throws Exception {
        mockMvc.perform(get("/CreateEmployee"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("employeeObj"))
                .andExpect(model().attributeExists("listOfSkills"))
                .andExpect(view().name("create-Employee"));
    }

    @Test
    void saveNewEmployee() throws Exception {
        List<String> listOfSkills = new ArrayList<>();
        listOfSkills.add("Developer");
        listOfSkills.add("Cost Controller");


        Employee employeeObj = new Employee("Egon Olsen", listOfSkills);


        mockMvc.perform(post("/saveEmployee")
                .flashAttr("employeeObj", employeeObj))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void getPMDashboard() throws Exception {
        int pmUserID = 1;
        mockMvc.perform(get("/pm-dashboard/{pmUserID}", pmUserID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("dashboardRows"))
                .andExpect(view().name("pm-Dashboard"));
    }

    @Test
    void testDeleteProject() throws Exception {
        int projectID = 1;

        mockMvc.perform(post("/delete-project/{projectID}", projectID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));
    }


    @Test
    void createSubTask() throws Exception{
        int taskID = 1;
        mockMvc.perform(get("/createSubTask/{taskID}", taskID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("create-SubTask"));
    }

    @Test
    void saveSubTask() throws Exception{
        SubTask subTask = new SubTask();
        mockMvc.perform(post("/saveSubTask", subTask))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));
    }

    @Test
    void deleteSubTask() throws Exception{
        int subTaskID = 1;
        mockMvc.perform(post("/deleteSubTask/{subTaskID}", subTaskID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

}










