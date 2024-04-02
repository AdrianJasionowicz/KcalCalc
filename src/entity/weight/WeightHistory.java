package entity.weight;

import java.time.LocalDate;

public class WeightHistory {
    private LocalDate date;
    private double weight;

    //   private double bmi;
    //   private User user;
    public WeightHistory(LocalDate date, double weight) {
        this.date = date;
        this.weight = weight;
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
/*
    private double calculateBMI() {
        double height = user.getHeight();
        bmi = weight / (height * height);
        return bmi;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

 */
}











