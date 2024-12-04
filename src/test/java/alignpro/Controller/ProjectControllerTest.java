package alignpro.Controller;

import alignpro.Controller.ProjectOverview.ProjectController;
import alignpro.Model.Projects.Project;
import alignpro.Service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)



class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    void testCreateNewProject() throws Exception {
        mockMvc.perform(get("/projects/CreateProject").sessionAttr("pmUserID", 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("create-Project"));
    }

    @Test
    void saveNewProject() throws Exception {
        Project project1 = new Project("New Project", "2024-11-24", "2024-11-26","Hehe det er en joke");
        mockMvc.perform(post("/projects/saveProject").sessionAttr("pmUserID", 1)
                        .flashAttr("projectObj",project1))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pm-dashboard"));
    }

    @Test
    void editProject() throws Exception {
        int projectId = 1;
        Project dummyProject = new Project(1,"dummy", "2024-11-11", "2024-11-22", "dummy project");
        when(projectService.getProject(1)).thenReturn(dummyProject);
        mockMvc.perform(get("/projects/edit-project/{projectId}", projectId).sessionAttr("pmUserID", 1))
                .andExpect(status().isOk())
                .andExpect(model().attribute("project", dummyProject))
                .andExpect(view().name("edit-project"));
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

        mockMvc.perform(post("/projects/updateProject").sessionAttr("pmUserID", 1)
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
    void testDeleteProject() throws Exception {
        int projectID = 1;

        mockMvc.perform(post("/projects/delete-project/{projectID}", projectID).sessionAttr("pmUserID", 1))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"))
                .andExpect(redirectedUrl("/"));
    }

}