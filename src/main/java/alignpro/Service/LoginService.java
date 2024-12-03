package alignpro.Service;

import alignpro.Model.ProjectManager;
import alignpro.Repository.Interfaces.ILoginRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final ILoginRepository loginRepository;

    public LoginService(ApplicationContext context, @Value("${login.repository.impl}") String impl) {
        this.loginRepository = (ILoginRepository) context.getBean(impl);
    }

    public ProjectManager getProjectManager(String mail) {
        return loginRepository.getProjectManager(mail);
    }



    public boolean loginCheck(String mail, String password) {
        ProjectManager projectManager = getProjectManager(mail);
        return projectManager.getMail().equals(mail) && projectManager.getPassword().equals(password);
    }
}
