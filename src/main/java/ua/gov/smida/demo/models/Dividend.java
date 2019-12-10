package ua.gov.smida.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Dividends")
public class Dividend {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "EDRPOU")
    private int edrpou;

    @Column(name = "Capital_Amount")
    private int capitalAmount;

    @Column(name = "Amount")
    private int amount;

    @Column(name = "Total_Nominal_Value")
    private double totalNominalValue;

    @Column(name = "Nominal_Value")
    private double nominalValue;

    @Column(name = "State_Duty_Paid")
    private double stateDutyPaid;

    @Column(name = "Release_Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime releaseDate;

    @Column(name = "Comment")
    private String comment;

    @JsonIgnore
    @Column(name = "Adding_Time")
    private LocalDateTime addTime;


    public Dividend() {}

    public Dividend(int id, int edrpou, int capitalAmount, int amount, double totalNominalValue, double nominalValue, double stateDutyPaid, LocalDateTime releaseDate, String comment) {
        this.id = id;
        this.edrpou = edrpou;
        this.capitalAmount = capitalAmount;
        this.amount = amount;
        this.totalNominalValue = totalNominalValue;
        this.nominalValue = nominalValue;
        this.stateDutyPaid = stateDutyPaid;
        this.releaseDate = releaseDate;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public int getEdrpou() {
        return edrpou;
    }

    public int getCapitalAmount() {
        return capitalAmount;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotalNominalValue() {
        return totalNominalValue;
    }

    public double getNominalValue() {
        return nominalValue;
    }

    public double getStateDutyPaid() {
        return stateDutyPaid;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEdrpou(int EDRPOU) {
        this.edrpou = EDRPOU;
    }

    public void setCapitalAmount(int capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotalNominalValue(double totalNominalValue) {
        this.totalNominalValue = totalNominalValue;
    }

    public void setNominalValue(double nominalValue) {
        this.nominalValue = nominalValue;
    }

    public void setStateDutyPaid(double stateDutyPaid) {
        this.stateDutyPaid = stateDutyPaid;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

}

