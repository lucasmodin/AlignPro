package alignpro.Repository;

import alignpro.Model.ProjectManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class LoginRepositoryTest {

    @Autowired
    LoginRepository loginRepository;

    @Test
    void getProjectManager() {

        String mailToCheck = "Lucas@Kea.dk";
        String mailThatDoesntExist ="doesntexist@kea.dk";

        ProjectManager projectManager = loginRepository.getProjectManager(mailToCheck);
        ProjectManager notRealProjectManager = loginRepository.getProjectManager(mailThatDoesntExist);

        assertEquals(projectManager.getMail(), mailToCheck);
        assertNull(notRealProjectManager);
    }
}