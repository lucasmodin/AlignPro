package alignpro.Model.DTOModel;

import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubTask;

import java.util.Iterator;
import java.util.List;

public class DashBoard_DTO {

    private List<ProjectDTO> projectList;
    private List<SubProjectDTO> subProjectList;
    private List<TaskDTO> taskList;
    private List<SubTaskDTO> subTaskList;

    //Empty Konstructor
    public DashBoard_DTO(){}


    //************************* Setter and getters *******************************//
    public List<ProjectDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectDTO> projectList) {
        this.projectList = projectList;
    }

    public List<SubProjectDTO> getSubProjectList() {
        return subProjectList;
    }

    public void setSubProjectList(List<SubProjectDTO> subProjectList) {
        this.subProjectList = subProjectList;
    }

    public List<TaskDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
        this.taskList = taskList;
    }

    public List<SubTaskDTO> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<SubTaskDTO> subTaskList) {
        this.subTaskList = subTaskList;
    }

    //***** methods to sort itself *****//

    public void filterList(String input) {
        Iterator<ProjectDTO> iterator = projectList.iterator();

        while (iterator.hasNext()) {
            ProjectDTO item = iterator.next();
            if (!item.getProjectName().contains(input)) {
                iterator.remove(); // Removes items that do not contain the input
            }
        }
    }

}

