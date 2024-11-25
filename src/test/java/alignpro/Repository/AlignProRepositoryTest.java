package alignpro.Repository;

import alignpro.Model.Project;
import alignpro.Model.SubProject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

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
/*
    @Test
    @DirtiesContext
    void saveSubProject(){
        SubProject objToSave = new SubProject("Test sub-project 1", "2024-11-25","2024-11-26","To test saveProject Method");

        alignProRepository.saveSubProject(objToSave.getSubProjectName(), objToSave.getStartDateString(),
                objToSave.getEndDateString(), objToSave.getSubProjectDescription());

        SubProject objToGet = alignProRepository.getSubProject(4);

        assertEquals(objToSave.getSubProjectName(), objToGet.getSubProjectName());
    }
*/
}
