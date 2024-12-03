package alignpro.Service;

import alignpro.Repository.Interfaces.ILoginController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final ILoginController loginController;

    public LoginService(ApplicationContext context, @Value("${login.repository.impl}") String impl) {
        this.loginController = (ILoginController) context.getBean(impl);
    }
}
