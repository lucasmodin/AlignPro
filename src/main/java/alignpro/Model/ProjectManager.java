package alignpro.Model;

public class ProjectManager {

    private int projectManagerID;
    private String fullName;
    private String mail;
    private String password;

    public ProjectManager(){}

    public ProjectManager(int projectManagerID, String fullName, String mail, String password) {
        this.projectManagerID = projectManagerID;
        this.fullName = fullName;
        this.mail = mail;
        this.password = password;
    }

    public ProjectManager(String fullName, String mail, String password) {
        this.fullName = fullName;
        this.mail = mail;
        this.password = password;
    }

    public int getProjectManagerID() {
        return projectManagerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setProjectManagerID(int projectManagerID) {
        this.projectManagerID = projectManagerID;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
