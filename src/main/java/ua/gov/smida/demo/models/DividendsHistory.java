package ua.gov.smida.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Dividends_History")
public class DividendsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @Column(name = "Adding_Time")
    @JsonIgnore
    private LocalDateTime addTime;


    public DividendsHistory() {}

    public DividendsHistory(int edrpou, int capitalAmount, int amount, double totalNominalValue, double nominalValue, double stateDutyPaid, LocalDateTime releaseDate, String comment, LocalDateTime addTime) {
        this.edrpou = edrpou;
        this.capitalAmount = capitalAmount;
        this.amount = amount;
        this.totalNominalValue = totalNominalValue;
        this.nominalValue = nominalValue;
        this.stateDutyPaid = stateDutyPaid;
        this.releaseDate = releaseDate;
        this.comment = comment;
        this.addTime = addTime;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getEdrpou() {
        return edrpou;
    }

    public void setEdrpou(int EDRPOU) { this.edrpou = EDRPOU; }

    public int getCapitalAmount() { return capitalAmount; }

    public void setCapitalAmount(int capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) { this.amount = amount; }

    public double getTotalNominalValue() {
        return totalNominalValue;
    }

    public void setTotalNominalValue(double totalNominalValue) {
        this.totalNominalValue = totalNominalValue;
    }

    public double getNominalValue() {
        return nominalValue;
    }

    public void setNominalValue(double nominalValue) {
        this.nominalValue = nominalValue;
    }

    public double getStateDutyPaid() { return stateDutyPaid; }

    public void setStateDutyPaid(double stateDutyPaid) {
        this.stateDutyPaid = stateDutyPaid;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

}
