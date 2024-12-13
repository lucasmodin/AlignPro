package alignpro.Service;

import alignpro.Model.Employee;
import alignpro.Model.Projects.SubTask;
import alignpro.Repository.Interfaces.ISubTaskRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubTaskService {

    private final ISubTaskRepository subTaskRepository;

    public SubTaskService(ApplicationContext context, @Value("${subtask.repository.impl}") String impl) {
        subTaskRepository = (ISubTaskRepository) context.getBean(impl);
    }

    public void editSubTask(SubTask subTask, int subTaskID){
        subTaskRepository.editSubTask(subTask, subTaskID);
    }

    public int saveSubTask(SubTask subTask) {
        return subTaskRepository.saveSubTask(subTask.getSubTaskName(), subTask.getStartDateString(), subTask.getEndDateString(),
                subTask.getTime(), subTask.getSubTaskDescription(), subTask.getSkillRequirement(), subTask.getTaskID());
    }

    public SubTask getSubTask(int subTaskID){
        return subTaskRepository.getSubTask(subTaskID);
    }

    public void deleteSubTask(int subTaskID){
        subTaskRepository.deleteSubTask(subTaskID);
    }

    public void assignEmployeeToTask(int subTaskID, int employeeID){
        subTaskRepository.assignEmployeeToTask(subTaskID, employeeID);
    }

    //helper method
    //the list is passed from pm-dashboard, but had trouble passing the whole list - had to loop through, and use hidden fields for ID and name, and build it again in here.
    public List<Employee> buildEmployeeList(List<Integer> employeeIDs, List<String> employeeNames) {
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < employeeIDs.size(); i++) {
            Employee employee = new Employee();
            employee.setEmployeeID(employeeIDs.get(i));
            employee.setEmployeeName(employeeNames.get(i));
            employeeList.add(employee);
        }
        return employeeList;
    }
}
