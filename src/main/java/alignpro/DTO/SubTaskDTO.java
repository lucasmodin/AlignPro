package alignpro.DTO;

public class SubTaskDTO {
    private String subTaskName;
    private String startDate;
    private String endDate;
    private int estimatedTime;

    public String getSubTaskName() {
        return subTaskName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
