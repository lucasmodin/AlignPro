package alignpro.Controller;

import alignpro.Model.ProjectManager;
import alignpro.Service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)


class LoginControllerTest {

    ProjectManager projectManager = new ProjectManager();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));

    }

    @Test
    void loginValidation() throws Exception {
        projectManager.setMail("Lucas@Kea.dk");
        projectManager.setPassword("Lucas1234");

        when(loginService.getProjectManager("Lucas@Kea.dk")).thenReturn(projectManager);
        when(loginService.loginCheck("Lucas@Kea.dk", "Lucas1234")).thenReturn(true);
        mockMvc.perform(post("/loginValidation")
                .param("mail", projectManager.getMail())
                .param("password", projectManager.getPassword())
                .sessionAttr("pmUserID", projectManager.getProjectManagerID()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}