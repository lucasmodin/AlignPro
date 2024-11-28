package alignpro.Repository;

import alignpro.Model.Project;
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
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;


    // this test, test both save and get Project. Perhaps project should be get differently.
    @Test
    @DirtiesContext
    void saveProject(){

        Project objToSave = new Project("Test project 1", "2024-11-24", "2024-11-25", "To test saveProject Method");

        projectRepository.saveProject(objToSave.getProjectName(),objToSave.getStartDateString(),objToSave.getDeadlineString(), objToSave.getProjectDescription());

        Project objToGet = projectRepository.getProject(4);

        assertEquals(objToSave.getProjectDescription(),objToGet.getProjectDescription());
    }

    @Test
    @DirtiesContext
    void getProject(){

        Project obj = projectRepository.getProject("Project Kea");

        assertEquals("Project Kea", obj.getProjectName());
    }

    @Test
    @DirtiesContext
    void editProject(){

        //Arrange
        Project originalProject = projectRepository.getProject(1);
        Project updatedProject = new Project(1, "project edit", "2024-11-24", "2024-11-25", "1-day project");


        //Act
        projectRepository.editProject(updatedProject, 1);

        //Assert
        Project fetchedProject = projectRepository.getProject(1);

        assertNotNull(fetchedProject);
        assertEquals(updatedProject.getProjectName(),fetchedProject.getProjectName());
        assertEquals(updatedProject.getStartDateString(),fetchedProject.getStartDateString());
        assertEquals(updatedProject.getDeadlineString(), fetchedProject.getDeadlineString());
        assertEquals(updatedProject.getProjectDescription(), fetchedProject.getProjectDescription());

        assertEquals(originalProject.getProjectID(), fetchedProject.getProjectID());
    }

    @Test
    @DirtiesContext
    void deleteProject(){

        Project objTest = projectRepository.getProject(1);

        assertTrue(objTest.getProjectName().equals("Project Kea"));

        projectRepository.deleteProject(1);

        Project objTest2 = projectRepository.getProject(1);

        assertNull(objTest2);
    }

}