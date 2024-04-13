package entity.activity;

import java.time.LocalDate;

public class Activity {
    private Integer id;
    private LocalDate date;
    private String activityName;
    private double timeInHours;
    private int exhaustedScale;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public double getTimeInHours() {
        return timeInHours;
    }

    public void setTimeInHours(double timeInHours) {
        this.timeInHours = timeInHours;
    }

    public int getExhaustedScale() {
        return exhaustedScale;
    }

    public void setExhaustedScale(int exhaustedScale) {
        this.exhaustedScale = exhaustedScale;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Activity(LocalDate date, String activityName, double timeInHours, int exhaustedScale, String description) {
        this.date = date;
        this.activityName = activityName;
        this.timeInHours = timeInHours;
        this.exhaustedScale = exhaustedScale;
        this.description = description;
    }
}
