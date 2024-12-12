package alignpro.Repository;

import alignpro.Model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
// NB Tests fail if the following line is not included as the h2 database is not reset between tests
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2init.sql")
public class AlignProRepositoryTest {

    @Autowired
    AlignProRepository alignProRepository;

    //Test to save and get employee from db
    @Transactional
    @Test
    void getEmployee(){

        Employee objTest = alignProRepository.getEmployee("Lars Larsen");

        assertEquals(1, objTest.getEmployeeID());
        assertTrue(objTest.getSkills().contains("Developer"));

    }

    @Transactional
    @Test
    void getListOfEmployees(){

        List<Employee> list = alignProRepository.getListOfEmployees();

        assertEquals(3,list.size());
        assertEquals("Kim Møller", list.get(1).getEmployeeName());
    }

    //*** test to save Employee to DB ***//
    @Transactional
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
    @Transactional
    @Test
    void getListOfSkills(){

        List<String> list = alignProRepository.getListOfSkills();

        assertTrue(list.contains("Developer"));
        assertTrue(list.contains("Cost Controller"));
    }
    @Transactional
    @Test
    void getSkillsID(){

        Map<String, Integer> mapSkills = alignProRepository.getSkillsID();

        assertEquals(1,mapSkills.get("Developer"));
        assertEquals(2,mapSkills.get("Cost Controller"));
        assertEquals(3,mapSkills.get("Project Manager"));
    }
    @Transactional
    @Test
    void deleteEmployee() {
        Employee objTest = alignProRepository.getEmployee(1);

        assertTrue(objTest.getEmployeeName().equals("Lars Larsen"));
        alignProRepository.deleteEmployee(1);
        Employee test = alignProRepository.getEmployee(1);

        assertNull(test);}
    @Transactional
    @Test
    void editEmployee(){

        Employee objToEdit = alignProRepository.getEmployee("Lars Larsen");

        assertEquals(1, objToEdit.getEmployeeID());
        assertTrue(objToEdit.getSkills().contains("Developer"));

        objToEdit.setEmployeeName("Kylling Lars");
        objToEdit.setEmployeeID(2);

        alignProRepository.editEmployee(objToEdit, 1);

        Employee editedObj = alignProRepository.getEmployee(1);

        assertEquals("Kylling Lars", editedObj.getEmployeeName());
        System.out.println(editedObj.getEmployeeID() + editedObj.getEmployeeName());
        assertEquals(1,editedObj.getEmployeeID());
    }

}
