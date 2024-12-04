package alignpro.Repository.Interfaces;

import alignpro.Model.ProjectManager;

public interface ILoginRepository {

    void setConn();

    ProjectManager getProjectManager(String mail);
}
