package entity.weight;

import java.time.LocalDate;

public class WeightHistory {
    private LocalDate date;
    private double weight;
    private double bmi;
    private double neck;
    private double chest;
    private double armBiceps;
    private double aboveNavel;
    private double navel;
    private double belowNavel;
    private double hips;
    private double thig;
    private double calf;

    public WeightHistory(LocalDate date, double weight, double bmi) {
        this.date = date;
        this.weight = weight;
        this.bmi = bmi;
    }

    public WeightHistory(double neck, double chest, double armBiceps, double aboveNavel, double navel, double belowNavel, double hips, double thig, double calf) {
        this.neck = neck;
        this.chest = chest;
        this.armBiceps = armBiceps;
        this.aboveNavel = aboveNavel;
        this.navel = navel;
        this.belowNavel = belowNavel;
        this.hips = hips;
        this.thig = thig;
        this.calf = calf;
    }

    public WeightHistory(LocalDate date, double weight) {
        this.date = date;
        this.weight = weight;
    }

    public WeightHistory(LocalDate measureDate, double neck, double chest, double armBiceps, double aboveNavel, double navel, double belowNavel, double hips, double thig, double calf) {
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

    public double getCalf() {
        return calf;
    }

    public void setCalf(double calf) {
        this.calf = calf;
    }

    public double getThig() {
        return thig;
    }

    public void setThig(double thig) {
        this.thig = thig;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public double getBelowNavel() {
        return belowNavel;
    }

    public void setBelowNavel(double belowNavel) {
        this.belowNavel = belowNavel;
    }

    public double getNavel() {
        return navel;
    }

    public void setNavel(double navel) {
        this.navel = navel;
    }

    public double getAboveNavel() {
        return aboveNavel;
    }

    public void setAboveNavel(double aboveNavel) {
        this.aboveNavel = aboveNavel;
    }

    public double getArmBiceps() {
        return armBiceps;
    }

    public void setArmBiceps(double armBiceps) {
        this.armBiceps = armBiceps;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}






