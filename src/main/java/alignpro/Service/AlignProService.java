package alignpro.Service;

import alignpro.Model.Employee;
import alignpro.Model.Project;
import alignpro.Repository.IFAlignProRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlignProService {

    private final IFAlignProRepository alignProRepository;

    public AlignProService (ApplicationContext context, @Value("${AlignPro.repository.impl}") String impl){
        alignProRepository = (IFAlignProRepository) context.getBean(impl);
    }

    //*** methods to handle Projects ***//
    public void saveProject(Project obj){
        alignProRepository.saveProject(obj.getProjectName(), obj.getStartDateString(), obj.getDeadlineString(), obj.getProjectDescription());
    }


    //*** methods to handle employees and skills ***//
    public void saveEmployee(Employee obj){
        alignProRepository.saveEmployee(obj.getEmployeeName(),obj.getSkills());
    }

    public List<String> getListOfSkills(){
        return alignProRepository.getListOfSkills();
    }

    public List<Employee> getListOfEmployees(){
        return alignProRepository.getListOfEmployees();
    }

}
