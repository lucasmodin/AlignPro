package alignpro.Repository;

import alignpro.Model.Projects.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
// NB Tests fail if the following line is not included as the h2 database is not reset between tests
class TaskRepositoryTest {

    @Autowired
    TaskRepository taskRepository;

    @Test
    void getTask(){
        Task task = taskRepository.getTask(1);

        assertEquals("RandomTask 42" ,task.getTaskName());
    }

    @Transactional
    @Test
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

    @Transactional
    @Test
    void saveTask(){
        Task objToSave = new Task("task 1", "2024-11-25", "2024-11-26", 5, "to be destroyed", "java", 1);

        taskRepository.saveTask(objToSave.getTaskName(), objToSave.getStartDateString(),
                objToSave.getEndDateString(),
                objToSave.getTaskDescription(), objToSave.getSkillRequirement(), 1);

        Task objToGet = taskRepository.getTask(3);

        assertEquals(objToSave.getTaskName(), objToGet.getTaskName());
    }

    @Transactional
    @Test
    void deleteTask(){
        Task task = new Task("task 1", "2024-11-25", "2024-11-26", 5, "to be destroyed", "java", 1);
        task.setTaskID(1);

        taskRepository.deleteTask(1);
        Task taskExpected = taskRepository.getTask(1);

        assertNull(taskExpected);
    }
}