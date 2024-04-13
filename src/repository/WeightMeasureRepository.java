package repository;

import entity.weight.WeightMeasure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeightMeasureRepository {
    private LocalDate date;
    private List<WeightMeasure> weightMeasures;

    public WeightMeasureRepository() {
        this.weightMeasures = new ArrayList<>();
    }

    public List<WeightMeasure> getMeasures() {
        return weightMeasures;
    }

    public void setMeasures(List<WeightMeasure> weightMeasures) {
        this.weightMeasures = weightMeasures;
    }

    public void addMeasure(WeightMeasure weightMeasure) {
        weightMeasures.add(weightMeasure);
    }

}