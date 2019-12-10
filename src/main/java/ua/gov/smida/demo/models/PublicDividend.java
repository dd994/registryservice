package ua.gov.smida.demo.models;

import java.time.LocalDateTime;

public class PublicDividend {

    private int edrpou;
    private int amount;
    private double nominalValue;
    private double totalNominalValue;
    private LocalDateTime releaseDate;

    public int getEdrpou() { return edrpou; }

    public void setEdrpou(int edrpou) { this.edrpou = edrpou; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getNominalValue() { return nominalValue; }

    public void setNominalValue(double nominalValue) { this.nominalValue = nominalValue; }

    public double getTotalNominalValue() { return totalNominalValue; }

    public void setTotalNominalValue(double totalNominalValue) { this.totalNominalValue = totalNominalValue; }

    public LocalDateTime getReleaseDate() { return releaseDate; }

    public void setReleaseDate(LocalDateTime releaseDate) { this.releaseDate = releaseDate; }

}
