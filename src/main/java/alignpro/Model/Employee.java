package alignpro.Model;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private int employeeID;
    private String employeeName;
    private List<String> skills = new ArrayList<>();

    public Employee(){}

    public Employee(String employeeName){
        this.employeeName = employeeName;
    }

    public Employee(String employeeName,List<String> skills){
        this.employeeName = employeeName;
        this.skills = skills;
    }

    public Employee(int employeeID, String employeeName){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
    }

    public Employee(int employeeID, String employeeName, List<String> skills){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.skills = skills;
    }

    //************************* Setter and getters *******************************//

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setSkills(String skill){
        skills.add(skill);
    }

    public void setSkills(List<String> skill){
        this.skills = skill;
    }

    public List<String> getSkills(){
        return skills;
    }
}
