package alignpro.Repository;

import alignpro.Model.Employee;
import alignpro.Model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
public class AlignProRepositoryTest {

    @Autowired
    AlignProRepository alignProRepository;



    // this test, test both save and get Project. Perhaps project should be get differently.
    @Test
    @DirtiesContext
    void saveProject(){

        Project objToSave = new Project("Test project 1", "2024-11-24","2024-11-25","To test saveProject Method");

        alignProRepository.saveProject(objToSave.getProjectName(),objToSave.getStartDateString(),objToSave.getDeadlineString(), objToSave.getProjectDescription());

        Project objToGet = alignProRepository.getProject(4);

        assertEquals(objToSave.getProjectDescription(),objToGet.getProjectDescription());
    }

    @Test
    @DirtiesContext
    void getProject(){

        Project obj = alignProRepository.getProject("Project Kea");

        assertEquals("Project Kea", obj.getProjectName());
    }

    //Test to save and get employee from db ***//
    @Test
    void getEmployee(){

        Employee objTest = alignProRepository.getEmployee("Lars Larsen");

        assertEquals(1, objTest.getEmployeeID());
        assertTrue(objTest.getSkills().contains("Developer"));

    }

    @Test
    void getListOfEmployees(){

        List<Employee> list = alignProRepository.getListOfEmployees();

        assertEquals(3,list.size());
        assertEquals("Kim Møller", list.get(1).getEmployeeName());
    }

    //*** test to save Employee to DB ***//
    @Test
    void saveEmployee(){

        List<String> listOfSkills = new ArrayList<>();
        listOfSkills.add("Developer");
        listOfSkills.add("Cost Controller");


        alignProRepository.saveEmployee("Ego Olsen", listOfSkills);

        Employee obj = alignProRepository.getEmployee("Ego Olsen");

        assertEquals("Ego Olsen", obj.getEmployeeName());
        assertTrue(obj.getSkills().contains("Cost Controller"));
    }

    //*** test to see if you get skills correctly ***//
    @Test
    void getListOfSkills(){

        List<String> list = alignProRepository.getListOfSkills();

        assertTrue(list.contains("Developer"));
        assertTrue(list.contains("Cost Controller"));
    }

    @Test
    void getSkillsID(){

        Map<String, Integer> mapSkills = alignProRepository.getSkillsID();

        assertEquals(1,mapSkills.get("Developer"));
        assertEquals(2,mapSkills.get("Cost Controller"));
        assertEquals(3,mapSkills.get("Project Manager"));
    }

}
