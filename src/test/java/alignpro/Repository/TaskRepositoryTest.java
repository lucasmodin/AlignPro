package alignpro.Repository;

import alignpro.Model.Projects.Task;
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
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    @DirtiesContext
    void getTask(){
        Task task = taskRepository.getTask(1);

        assertEquals("RandomTask 42" ,task.getTaskName());
    }

    @Test
    @DirtiesContext
    void editTask(){

        //Arrange
        Task originalTask = taskRepository.getTask(1);
        Task updatedTask = new Task("task 1", "2024-11-25", "2024-11-26", 5, "to be destroyed", "java", 1);


        //Act
        taskRepository.editTask(updatedTask, 1);

        //Assert
        Task fetchedProject = taskRepository.getTask(1);

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

        taskRepository.saveTask(objToSave.getTaskName(), objToSave.getStartDateString(),
                objToSave.getEndDateString(), objToSave.getEstimatedTime(),
                objToSave.getTaskDescription(), objToSave.getSkillRequirement(), 1);

        Task objToGet = taskRepository.getTask(3);

        assertEquals(objToSave.getTaskName(), objToGet.getTaskName());
    }

    @Test
    @DirtiesContext
    void deleteTask(){
        Task task = new Task("task 1", "2024-11-25", "2024-11-26", 5, "to be destroyed", "java", 1);
        task.setTaskID(1);

        taskRepository.deleteTask(1);
        Task taskExpected = taskRepository.getTask(1);

        assertNull(taskExpected);
    }
}