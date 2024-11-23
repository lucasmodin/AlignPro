package alignpro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2")

public class AlignProControllerTest {

    @Autowired
    AlignProController alignProController;

}
