package alignpro.Repository;

import alignpro.Model.Employee;
import alignpro.Model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

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

        alignProRepository.saveEmployee("Ego Olsen");

        Employee obj = alignProRepository.getEmployee("Ego Olsen");

        assertEquals("Ego Olsen", obj.getEmployeeName());
    }

}
