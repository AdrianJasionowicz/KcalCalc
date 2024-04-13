package entity.weight;

import java.time.LocalDate;

public class WeightHistory {
    private LocalDate date;
    private double weight;
    private double bmi;

    public WeightHistory(LocalDate date, double weight) {
        this.date = date;
        this.weight = weight;
    }

    public WeightHistory(LocalDate date, double weight, double bmi) {
        this.date = date;
        this.weight = weight;
        this.bmi = bmi;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

}











