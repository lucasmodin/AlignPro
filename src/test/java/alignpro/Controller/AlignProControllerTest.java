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

    @Test
    void deleteEmployee() throws Exception{
        int employeeID = 1;
        mockMvc.perform(post("/deleteEmployee/{employeeID}", employeeID))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void updateEmployee() throws Exception {
        List<String> listOfSkills = new ArrayList<>();
        int employeeID = 1;
        String name = "Lars kylling";
        listOfSkills.add("Developer");
        listOfSkills.add("Cost Controller");

        mockMvc.perform(post("/updateEmployee")
                        .param("employeeID", String.valueOf(employeeID))
                        .param("employeeName", name )
                        .param("skills", String.valueOf(listOfSkills)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(view().name("redirect:/"));

    }







}










