package repository;

import entity.weight.WeightHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeightHistoryRepository {
    private LocalDate date;
    private List<WeightHistory> weightHistories = new ArrayList<>();
    private List<WeightHistory> measuresHistory = new ArrayList<>();


    public WeightHistoryRepository(LocalDate date) {
        this.date = date;
    }

    public List<WeightHistory> getWeightHistories() {
        return weightHistories;
    }

    public void setWeightHistories(List<WeightHistory> weightHistories) {
        this.weightHistories = weightHistories;
    }

    public void showHistory() {
        List<WeightHistory> weightHistoryList = getWeightHistories();
        if (weightHistoryList.isEmpty()) {
            System.out.println("Brak historii wag.");
        } else {
            System.out.println("Historia wag:");
            for (WeightHistory weightHistory : weightHistoryList) {
                System.out.println("Data: " + weightHistory.getDate() + ", Waga: " + weightHistory.getWeight());
            }
        }
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

    public List<WeightHistory> getMeasuresHistory() {
        return measuresHistory;
    }

    public void addMeasure(LocalDate date, WeightHistory weightHistory) {
        measuresHistory.add(weightHistory);
    }

    public void editMeasure(LocalDate date, WeightHistory weightHistory) {
        for (WeightHistory measures : measuresHistory) {
            if (measures.getDate().equals(date)) {
                measures.setNeck(weightHistory.getNeck());
                measures.setChest(weightHistory.getChest());
                measures.setArmBiceps(weightHistory.getArmBiceps());
                measures.setAboveNavel(weightHistory.getAboveNavel());
                measures.setNavel(weightHistory.getNavel());
                measures.setBelowNavel(weightHistory.getBelowNavel());
                measures.setHips(weightHistory.getHips());
                measures.setThig(weightHistory.getThig());
                measures.setCalf(weightHistory.getCalf());

            }

        }
    }

    public void removeMeasure(LocalDate date) {
        for (WeightHistory measures : measuresHistory) {
            if (measures.getDate().equals(date)) {
                measuresHistory.remove(measures);
            }
        }
    }

public WeightHistory findByDate(LocalDate date) {
    for (WeightHistory measures : measuresHistory) {
        if (measures.getDate().equals(date)) {
            return measures;
        }
    } return null;
}

}