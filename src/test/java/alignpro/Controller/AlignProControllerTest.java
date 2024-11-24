package alignpro.Controller;

import alignpro.Service.AlignProService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

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


    @Test
    void testCreateNewProject() throws Exception {
        mockMvc.perform(get("/AlignPro/CreateProject"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("obj"))
                .andExpect(view().name("create-Project"));
    }

    @Test
    void saveNewProject() throws Exception {
        mockMvc.perform(post("/saveProject")
                .param("projectName", "New Project")
                                .param("startDate", "2024-11-24")
                                .param("deadline", "2024-11-26")
                                .param("projectDescription", "Hehe det er en joke"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/AlignPro"))
                .andExpect(redirectedUrl("/AlignPro"));
    }

}
