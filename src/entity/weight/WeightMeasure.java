package entity.weight;

import java.time.LocalDate;

public class WeightMeasure extends WeightHistory {
    private double neck;
    private double chest;
    private double armBiceps;
    private double aboveNavel;
    private double navel;
    private double belowNavel;
    private double hips;
    private double thig;
    private double calf;


    public WeightMeasure(LocalDate date, double weight, double neck, double chest, double armBiceps, double aboveNavel, double navel, double belowNavel, double hips, double thig, double calf) {
        super(date, weight);
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

    public double getNeck() {
        return neck;
    }

    public void setNeck(double neck) {
        this.neck = neck;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getArmBiceps() {
        return armBiceps;
    }

    public void setArmBiceps(double armBiceps) {
        this.armBiceps = armBiceps;
    }

    public double getAboveNavel() {
        return aboveNavel;
    }

    public void setAboveNavel(double aboveNavel) {
        this.aboveNavel = aboveNavel;
    }

    public double getNavel() {
        return navel;
    }

    public void setNavel(double navel) {
        this.navel = navel;
    }

    public double getBelowNavel() {
        return belowNavel;
    }

    public void setBelowNavel(double belowNavel) {
        this.belowNavel = belowNavel;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public double getThig() {
        return thig;
    }

    public void setThig(double thig) {
        this.thig = thig;
    }

    public double getCalf() {
        return calf;
    }

    public void setCalf(double calf) {
        this.calf = calf;
    }
}
