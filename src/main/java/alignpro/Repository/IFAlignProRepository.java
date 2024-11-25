package alignpro.Repository;

import alignpro.Model.Employee;
import alignpro.Model.Project;

import java.util.List;

public interface IFAlignProRepository {

    void setConn();

    //Methods to manage project;
    void saveProject(String projectName, String startDate,String deadLine, String projectDescription);

    Project getProject(String projectName);

    Project getProject(int ProjectID);

    //void editProject();

    // Methods to manage Employees

    Employee getEmployee();

    // Not sure this method is necessary
    Employee getEmployee(String employeeName);

    List<Employee> getListOfEmployees();

}
