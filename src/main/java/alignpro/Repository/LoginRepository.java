package alignpro.Repository;

import alignpro.Repository.Interfaces.ILoginController;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Repository("LOGIN_REPOSITORY_JDBC")
@Lazy
public class LoginRepository implements ILoginController {






}
