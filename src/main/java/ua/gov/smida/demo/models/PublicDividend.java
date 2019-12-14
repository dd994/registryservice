package ua.gov.smida.demo.models;

import java.time.LocalDate;

public class PublicDividend  {

    private int edrpou;
    private int amount;
    private double nominalValue;
    private double totalNominalValue;
    private LocalDate releaseDate;

    public int getEdrpou() {
        return edrpou;
    }

    public int getAmount() {
        return amount;
    }

    public double getNominalValue() {
        return nominalValue;
    }

    public double getTotalNominalValue() {
        return totalNominalValue;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setEdrpou(int edrpou) {
        this.edrpou = edrpou;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setNominalValue(double nominalValue) {
        this.nominalValue = nominalValue;
    }

    public void setTotalNominalValue(double totalNominalValue) {
        this.totalNominalValue = totalNominalValue;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
