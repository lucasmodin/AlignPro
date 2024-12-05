package alignpro.Model.DTOModel;

import alignpro.Model.Projects.Project;
import alignpro.Model.Projects.SubProject;
import alignpro.Model.Projects.SubTask;
import alignpro.Model.Projects.Task;

import java.util.ArrayList;
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

    //**** Method to return a list to pick how to sort ****//

    public List<String> filterList(){
        List<String> filterList = new ArrayList<>();

        for (ProjectDTO project : projectList){
            filterList.add(project.getProjectName());
        }

        return filterList;
    }


    //***** methods to sort itself *****//

    public void filter(String input){
        filterProjects(input);
        filterSubProjects(input);
        filterTask(input);
        filterSubTask(input);
    }

    public void filterProjects(String input) {
        Iterator<ProjectDTO> iterator = projectList.iterator();

        while (iterator.hasNext()) {
            ProjectDTO item = iterator.next();
            if (!item.getProjectName().contains(input)) {
                iterator.remove(); // Removes items that do not contain the input
            }
        }
    }

    public void filterSubProjects(String input){
        Iterator<SubProjectDTO> iterator = subProjectList.iterator();

        while (iterator.hasNext()) {
            SubProjectDTO item = iterator.next();
            if (!item.getFilter().contains(input)) {
                iterator.remove(); // Removes items that do not contain the input
            }
        }
    }

    public void filterTask(String input){
        Iterator<TaskDTO> iterator = taskList.iterator();

        while (iterator.hasNext()) {
            TaskDTO item = iterator.next();
            if (!item.getFilter().contains(input)) {
                iterator.remove(); // Removes items that do not contain the input
            }
        }
    }

    public void filterSubTask(String input){
        Iterator<SubTaskDTO> iterator = subTaskList.iterator();

        while (iterator.hasNext()) {
            SubTaskDTO item = iterator.next();
            if (!item.getFilter().contains(input)) {
                iterator.remove(); // Removes items that do not contain the input
            }
        }
    }



}

