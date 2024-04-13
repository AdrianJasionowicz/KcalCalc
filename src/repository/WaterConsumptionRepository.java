package repository;

import entity.water.WaterConsumptionToday;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WaterConsumptionRepository {
    private final Map<LocalDate, WaterConsumptionToday> historyMap;

    public WaterConsumptionRepository() {
        historyMap = new HashMap<>();
    }

    public void addEntry(LocalDate date, WaterConsumptionToday data) {
        historyMap.put(date, data);
    }

    public WaterConsumptionToday getEntry(LocalDate date) {
        return historyMap.get(date);
    }

    public Map<LocalDate, WaterConsumptionToday> getHistoryMap() {
        return historyMap;
    }
}