package alignpro.Repository;

import alignpro.Model.SubProject;
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
class SubProjectRepositoryTest {

    @Autowired
    SubProjectRepository subProjectRepository;

    @Test
    @DirtiesContext
    void saveSubProject(){
        SubProject objToSave = new SubProject("Test sub-project 1", "2024-11-25","2024-11-26","To test saveSubProject Method");

        subProjectRepository.saveSubProject(objToSave.getSubProjectName(), objToSave.getStartDateString(),
                objToSave.getEndDateString(), objToSave.getSubProjectDescription(), 1);

        SubProject objToGet = subProjectRepository.getSubProject(4);

        assertEquals(objToSave.getSubProjectName(), objToGet.getSubProjectName());
    }


    @Test
    @DirtiesContext
    void getSubProject(){

        SubProject obj = subProjectRepository.getSubProject("Workd day and night");

        assertEquals("Workd day and night", obj.getSubProjectName());
    }

    @Test
    @DirtiesContext
    void deleteSubProject(){
        SubProject objToSave = new SubProject("Test sub-project 1", "2024-11-25", "2024-11-26", "To test deleteSubProject Method");

        subProjectRepository.saveSubProject(objToSave.getSubProjectName(), objToSave.getStartDateString(),
                objToSave.getEndDateString(), objToSave.getSubProjectDescription(), 1);

        subProjectRepository.deleteSubProject(4);
        SubProject objToGet = subProjectRepository.getSubProject(4);

        assertNull(objToGet);
    }

}