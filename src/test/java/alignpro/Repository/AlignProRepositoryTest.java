package alignpro.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("h2")
public class AlignProRepositoryTest {

    @Autowired
    AlignProRepository alignProRepository;

}
