package alignpro.Service;

import alignpro.Model.ProjectManager;
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

    public ProjectManager getProjectManager(String mail) {
        return loginController.getProjectManager(mail);
    }



    public boolean loginCheck(String mail, String password) {
        ProjectManager projectManager = getProjectManager(mail);
        if (projectManager.getMail().equals(mail) && projectManager.getPassword().equals(password)) {
            return true;
        } else return false;
    }
}
