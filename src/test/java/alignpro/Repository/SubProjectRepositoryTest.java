package alignpro.Repository;

import alignpro.Model.Projects.SubProject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SubProjectRepositoryTest {

    @Autowired
    SubProjectRepository subProjectRepository;

    @Transactional
    @Test
    void saveSubProject(){
        SubProject objToSave = new SubProject("Test sub-project 1", "2024-11-25", "2024-11-26", "To test saveSubProject Method");
        subProjectRepository.saveSubProject(
                objToSave.getSubProjectName(),
                objToSave.getStartDateString(),
                objToSave.getEndDateString(),
                objToSave.getSubProjectDescription(),
                1);


        SubProject objToGet = subProjectRepository.getSubProject(4);
        assertEquals(objToSave.getSubProjectName(), objToGet.getSubProjectName());
    }

    @Transactional
    @Test
    void getSubProject(){

        SubProject objToSave = new SubProject("Test sub-project 1", "2024-11-25", "2024-11-26", "To test getSubProject Method");
        subProjectRepository.saveSubProject(
                objToSave.getSubProjectName(),
                objToSave.getStartDateString(),
                objToSave.getEndDateString(),
                objToSave.getSubProjectDescription(),
                1);


        SubProject retrievedSubProject = subProjectRepository.getSubProject(4);


        assertNotNull(retrievedSubProject);
        assertEquals(objToSave.getSubProjectName(), retrievedSubProject.getSubProjectName());
    }

    @Transactional
    @Test
    void deleteSubProject(){

        SubProject objToSave = new SubProject("Test sub-project 1", "2024-11-25", "2024-11-26", "To test deleteSubProject Method");
        subProjectRepository.saveSubProject(
                objToSave.getSubProjectName(),
                objToSave.getStartDateString(),
                objToSave.getEndDateString(),
                objToSave.getSubProjectDescription(),
                1);


        subProjectRepository.deleteSubProject(4);


        SubProject retrievedSubProject = subProjectRepository.getSubProject(4);
        assertNull(retrievedSubProject);
    }

}