package repository;

import entity.weight.WeightHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeightHistoryRepository {
    private LocalDate date;
    private List<WeightHistory> weightHistories;

    public WeightHistoryRepository(LocalDate date) {
        this.date = date;
        this.weightHistories = new ArrayList<>();
    }

    public List<WeightHistory> getWeightHistories() {
        return weightHistories;
    }

    public void setWeightHistories(List<WeightHistory> weightHistories) {
        this.weightHistories = weightHistories;
    }

    public void addWeightHistory(WeightHistory weightHistory) {
        weightHistories.add(weightHistory);
    }

    public void add(LocalDate date, double weight) {
        WeightHistory weightHistory = new WeightHistory(date, weight);
        weightHistories.add(weightHistory);
    }

    public void setWeight(LocalDate date, double weight) {
        boolean found = false;
        for (WeightHistory history : weightHistories) {
            if (history.getDate().equals(date)) {
                history.setWeight(weight);
                found = true;
                break;
            }
        }
        if (!found) {
            add(date, weight);
        }
    }

    public void showHistory() {
        System.out.println("Historia pomiarów:");
        for (WeightHistory history : weightHistories) {
            System.out.println("Data: " + history.getDate() + ", Waga: " + history.getWeight()  +  ", Bmi: " + history.getBmi() );
        }
    }
}