package alignpro.Controller;

import alignpro.Controller.ProjectOverview.SubProjectController;
import alignpro.Model.Projects.SubProject;
import alignpro.Service.SubProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubProjectController.class)

class SubProjectControllerTest {

    private final int pmUserID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubProjectService subProjectService;

    @Test
    void testCreateNewSubProject() throws Exception {
        int projectID = 1;
        mockMvc.perform(get("/subProjects/createSubProject/{projectID}", projectID).sessionAttr("pmUserID", pmUserID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("createHTML/create-SubProject"));
    }

    @Test
    void saveNewSubProject() throws Exception {
        SubProject subProject = new SubProject("make crud functions", "2024-11-25", "2024-11-26", "make all crud now!");

        mockMvc.perform(post("/subProjects/saveSubProject").sessionAttr("pmUserID", pmUserID)
                        .flashAttr("subProject", subProject))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pm-dashboard/" + pmUserID));
    }

    @Test
    void deleteSubProject() throws Exception{
        int subProjectId = 1;

        mockMvc.perform(post("/subProjects/deleteSubProject/{subProjectID}", subProjectId).sessionAttr("pmUserID", pmUserID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pm-dashboard/" + pmUserID));
    }

    @Test
    void editSubProject() throws Exception{
        int subProjectID = 1;
        SubProject dummySubProject = new SubProject(1,"dummy", "2024-11-11", "2024-11-22", "dummy project");
        when(subProjectService.getSubProject(1)).thenReturn(dummySubProject);
        mockMvc.perform(get("/subProjects/edit-subproject/{subProjectID}", subProjectID).sessionAttr("pmUserID", pmUserID))
                .andExpect(status().isOk())
                .andExpect(model().attribute("subProject", dummySubProject))
                .andExpect(view().name("editHTML/edit-SubProject"));
    }

    @Test
    void updateSubProject() throws Exception{
        int subProjectID = 1;
        String subProjectName = "dummy project";
        String startDate = "2024-11-11";
        String endDate = "2024-11-22";
        String subProjectDescription = "dummy project";

        mockMvc.perform(post("/subProjects/updateSubProject").sessionAttr("pmUserID", pmUserID)
                        .param("subProjectID", String.valueOf(subProjectID))
                        .param("subProjectName",subProjectName)
                        .param("startDate", startDate)
                        .param("endDate", endDate)
                        .param("subProjectDescription",subProjectDescription))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pm-dashboard/" + pmUserID))
                .andExpect(view().name("redirect:/pm-dashboard/" + pmUserID));
    }

}