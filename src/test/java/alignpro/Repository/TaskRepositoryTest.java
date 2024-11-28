package alignpro.Repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("h2")
// NB Tests fail if the following line is not included as the h2 database is not reset between tests
//@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:h2init.sql")
class TaskRepositoryTest {

}