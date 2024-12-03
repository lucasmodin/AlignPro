package alignpro.Repository.Interfaces;

import alignpro.Model.ProjectManager;

public interface ILoginController {

    void setConn();

    ProjectManager getProjectManager(String mail);
}
