package alignpro.Controller;

import alignpro.Controller.ProjectOverview.AlignProController;
import alignpro.Model.*;
import alignpro.Service.AlignProService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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






}










