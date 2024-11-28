package alignpro.Repository;

import alignpro.Model.SubTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("h2")
// NB Tests fail if the following line is not included as the h2 database is not reset between tests
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2init.sql")
class SubTaskRepositoryTest {

    @Autowired
    SubTaskRepository subTaskRepository;

    @Test
    @DirtiesContext
    void saveSubTask(){

        SubTask objToSave = new SubTask("sub project test", "please go through test", "2024-11-11", "2024-11-22", 50,"coding", 1);


        subTaskRepository.saveSubTask(objToSave.getSubTaskName(), objToSave.getStartDateString(), objToSave.getEndDateString(), 50, objToSave.getSubTaskDescription(), objToSave.getSkillRequirement(), objToSave.getTaskID());

        SubTask objToGet = subTaskRepository.getSubTask(3);

        assertEquals(objToSave.getSubTaskName(),objToGet.getSubTaskName());


    }

    @Test
    @DirtiesContext
    void getSubTask() {
        SubTask objToGet = subTaskRepository.getSubTask(1);
        assertEquals(objToGet.getSubTaskName(),"Build a gun");
    }

    @Test
    @DirtiesContext
    void editSubTask() {
        //Arrange
        //had to make new object and save it to the database, as the in the context of the other tests, the populated ID's from the h2init bugs (ID 1 and 2), but works fine isolated
        //it works in context now
        SubTask subTaskToSave = new SubTask(3, "SubTask 3", "more work? off i go then","2025-11-25", "2025-11-25",5, "java");

        subTaskRepository.saveSubTask(subTaskToSave.getSubTaskName(), subTaskToSave.getStartDateString(), subTaskToSave.getEndDateString(),
                                      subTaskToSave.getTime(), subTaskToSave.getSubTaskDescription(),
                                      subTaskToSave.getSkillRequirement(), 1);

        SubTask originalSubTask = subTaskRepository.getSubTask(3);
        SubTask updatedSubTask = new SubTask(3, "SubTask 3", "more work? off i go then", "2025-11-25", "2025-11-25", 5, "java");


        //Act
        subTaskRepository.editSubTask(updatedSubTask, 3);

        //Assert
        SubTask fetchedProject = subTaskRepository.getSubTask(3);

        //assertNotNull(fetchedProject);
        assertEquals(updatedSubTask.getSubTaskName(),fetchedProject.getSubTaskName());
        assertEquals(updatedSubTask.getStartDateString(),fetchedProject.getStartDateString());
        assertEquals(updatedSubTask.getEndDateString(), fetchedProject.getEndDateString());
        assertEquals(updatedSubTask.getSubTaskDescription(), fetchedProject.getSubTaskDescription());

        assertEquals(originalSubTask.getSubTaskID(), fetchedProject.getSubTaskID());
    }

    @Test
    void testDeleteSubTask(){
        SubTask objTest = subTaskRepository.getSubTask(1);

        assertTrue(objTest.getSubTaskName().equals("Build a gun"));

        subTaskRepository.deleteSubTask(1);

        SubTask objTest2 = subTaskRepository.getSubTask(1);

        assertNull(objTest2);
    }


}