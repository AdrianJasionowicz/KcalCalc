package entity.water;

import java.time.LocalDate;

public class WaterConsumptionToday {
    private final LocalDate date;
    private int waterConsumption = 0;
    private int dailyTarget;


    public WaterConsumptionToday(LocalDate date, int waterConsumption, int dailyTarget) {
        this.date = date;
        this.waterConsumption = waterConsumption;
        this.dailyTarget = dailyTarget;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getWaterConsumption() {
        return waterConsumption;
    }

    public void setWaterConsumption(int waterConsumption) {
        this.waterConsumption = waterConsumption;
    }

    public int getDailyTarget() {
        return dailyTarget;
    }

    public void setDailyTarget(int dailyTarget) {
        this.dailyTarget = dailyTarget;
    }

}
