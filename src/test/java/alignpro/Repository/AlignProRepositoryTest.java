package alignpro.Repository;

import alignpro.Model.Employee;
import alignpro.Model.Project;
import alignpro.Model.SubProject;
import alignpro.Model.SubTask;
import alignpro.Model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("h2")
// NB Tests fail if the following line is not included as the h2 database is not reset between tests
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2init.sql")
public class AlignProRepositoryTest {

    @Autowired
    AlignProRepository alignProRepository;



    // this test, test both save and get Project. Perhaps project should be get differently.
    @Test
    @DirtiesContext
    void saveProject(){

        Project objToSave = new Project("Test project 1", "2024-11-24", "2024-11-25", "To test saveProject Method");

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


    @Test
    @DirtiesContext
    void saveSubProject(){
        SubProject objToSave = new SubProject("Test sub-project 1", "2024-11-25","2024-11-26","To test saveSubProject Method");

        alignProRepository.saveSubProject(objToSave.getSubProjectName(), objToSave.getStartDateString(),
                objToSave.getEndDateString(), objToSave.getSubProjectDescription(), 1);

        SubProject objToGet = alignProRepository.getSubProject(4);

        assertEquals(objToSave.getSubProjectName(), objToGet.getSubProjectName());
    }


    @Test
    @DirtiesContext
    void getSubProject(){

        SubProject obj = alignProRepository.getSubProject("Workd day and night");

        assertEquals("Workd day and night", obj.getSubProjectName());
    }

    @Test
    @DirtiesContext
    void deleteSubProject(){
        SubProject objToSave = new SubProject("Test sub-project 1", "2024-11-25", "2024-11-26", "To test deleteSubProject Method");

        alignProRepository.saveSubProject(objToSave.getSubProjectName(), objToSave.getStartDateString(),
                objToSave.getEndDateString(), objToSave.getSubProjectDescription(), 1);

        alignProRepository.deleteSubProject(4);
        SubProject objToGet = alignProRepository.getSubProject(4);

        assertNull(objToGet);
    }

    @Test
    @DirtiesContext
    void getTask(){
        Task task = alignProRepository.getTask(1);

        assertEquals("RandomTask 42" ,task.getTaskName());
    }

    @Test
    @DirtiesContext
    void editTask(){

        //Arrange
        Task originalTask = alignProRepository.getTask(1);
        Task updatedTask = new Task("task 1", "2024-11-25", "2024-11-26", 5, "to be destroyed", "java", 1);


        //Act
        alignProRepository.editTask(updatedTask, 1);

        //Assert
        Task fetchedProject = alignProRepository.getTask(1);

        assertNotNull(fetchedProject);
        assertEquals(updatedTask.getTaskName(),fetchedProject.getTaskName());
        assertEquals(updatedTask.getStartDateString(),fetchedProject.getStartDateString());
        assertEquals(updatedTask.getEndDateString(), fetchedProject.getEndDateString());
        assertEquals(updatedTask.getTaskDescription(), fetchedProject.getTaskDescription());

        assertEquals(originalTask.getTaskID(), fetchedProject.getTaskID());
    }

    @Test
    @DirtiesContext
    void saveTask(){
        Task objToSave = new Task("task 1", "2024-11-25", "2024-11-26", 5, "to be destroyed", "java", 1);

        alignProRepository.saveTask(objToSave.getTaskName(), objToSave.getStartDateString(),
                objToSave.getEndDateString(), objToSave.getEstimatedTime(),
                objToSave.getTaskDescription(), objToSave.getSkillRequirement(), 1);

        Task objToGet = alignProRepository.getTask(3);

        assertEquals(objToSave.getTaskName(), objToGet.getTaskName());
    }

    @Test
    @DirtiesContext
    void deleteTask(){
        Task task = new Task("task 1", "2024-11-25", "2024-11-26", 5, "to be destroyed", "java", 1);
        task.setTaskID(1);

        alignProRepository.deleteTask(1);
        Task taskExpected = alignProRepository.getTask(1);

        assertNull(taskExpected);
    }

    @Test
    @DirtiesContext
    void editProject(){

        //Arrange
        Project originalProject = alignProRepository.getProject(1);
        Project updatedProject = new Project(1, "project edit", "2024-11-24", "2024-11-25", "1-day project");


        //Act
        alignProRepository.editProject(updatedProject, 1);

        //Assert
        Project fetchedProject = alignProRepository.getProject(1);

        assertNotNull(fetchedProject);
        assertEquals(updatedProject.getProjectName(),fetchedProject.getProjectName());
        assertEquals(updatedProject.getStartDateString(),fetchedProject.getStartDateString());
        assertEquals(updatedProject.getDeadlineString(), fetchedProject.getDeadlineString());
        assertEquals(updatedProject.getProjectDescription(), fetchedProject.getProjectDescription());

        assertEquals(originalProject.getProjectID(), fetchedProject.getProjectID());
    }

    @Test
    @DirtiesContext
    void deleteProejct(){

        Project objTest = alignProRepository.getProject(1);

        assertTrue(objTest.getProjectName().equals("Project Kea"));

        alignProRepository.deleteProject(1);

        Project objTest2 = alignProRepository.getProject(1);

        assertNull(objTest2);
    }



    //Test to save and get employee from db
    @Test
    @DirtiesContext
    void getEmployee(){

        Employee objTest = alignProRepository.getEmployee("Lars Larsen");

        assertEquals(1, objTest.getEmployeeID());
        assertTrue(objTest.getSkills().contains("Developer"));

    }


    @Test
    @DirtiesContext
    void getListOfEmployees(){

        List<Employee> list = alignProRepository.getListOfEmployees();

        assertEquals(3,list.size());
        assertEquals("Kim Møller", list.get(1).getEmployeeName());
    }

    //*** test to save Employee to DB ***//
    @Test
    @DirtiesContext
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
    @DirtiesContext
    void getListOfSkills(){

        List<String> list = alignProRepository.getListOfSkills();

        assertTrue(list.contains("Developer"));
        assertTrue(list.contains("Cost Controller"));
    }

    @Test
    @DirtiesContext
    void getSkillsID(){

        Map<String, Integer> mapSkills = alignProRepository.getSkillsID();

        assertEquals(1,mapSkills.get("Developer"));
        assertEquals(2,mapSkills.get("Cost Controller"));
        assertEquals(3,mapSkills.get("Project Manager"));
    }

    @Test
    @DirtiesContext
    void saveSubTask(){

        SubTask objToSave = new SubTask("sub project test", "please go through test", "2024-11-11", "2024-11-22", 50,"coding", 1);


        alignProRepository.saveSubTask(objToSave.getSubTaskName(), objToSave.getStartDateString(), objToSave.getEndDateString(), 50, objToSave.getSubTaskDescription(), objToSave.getSkillRequirement(), objToSave.getTaskID());

        SubTask objToGet = alignProRepository.getSubTask(3);

        assertEquals(objToSave.getSubTaskName(),objToGet.getSubTaskName());


    }

    @Test
    @DirtiesContext
    void getSubTask() {
        SubTask objToGet = alignProRepository.getSubTask(1);
        assertEquals(objToGet.getSubTaskName(),"Build a gun");
    }

    @Test
    @DirtiesContext
    void editSubTask() {
        //Arrange
        SubTask originalSubTask = alignProRepository.getSubTask(1);
        SubTask updatedSubTask = new SubTask(1, "SubTask 2", "task task doooo", "2025-11-25", "2025-11-25", 5, "java");


        //Act
        alignProRepository.editSubTask(updatedSubTask, 1);

        //Assert
        SubTask fetchedProject = alignProRepository.getSubTask(1);

        assertNotNull(fetchedProject);
        assertEquals(updatedSubTask.getSubTaskName(),fetchedProject.getSubTaskName());
        assertEquals(updatedSubTask.getStartDateString(),fetchedProject.getStartDateString());
        assertEquals(updatedSubTask.getEndDateString(), fetchedProject.getEndDateString());
        assertEquals(updatedSubTask.getSubTaskDescription(), fetchedProject.getSubTaskDescription());

        assertEquals(originalSubTask.getSubTaskID(), fetchedProject.getSubTaskID());
    }

    @Test
    void testDeleteSubTask(){
        SubTask objTest = alignProRepository.getSubTask(1);

        assertTrue(objTest.getSubTaskName().equals("Build a gun"));

        alignProRepository.deleteSubTask(1);

        SubTask objTest2 = alignProRepository.getSubTask(1);

        assertNull(objTest2);
    }



}
