package ua.gov.smida.demo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Dividends")
public class Dividend {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(name = "Edrpou")
    private int edrpou;

    @Column(name = "Capital_Amount")
    private Integer capitalAmount;

    @Column(name = "Amount")
    private int amount;

    @Column(name = "Total_Nominal_Value")
    private double totalNominalValue;

    @Column(name = "Nominal_Value")
    private double nominalValue;

    @Column(name = "State_Duty_Paid")
    private double stateDutyPaid;

    @Column(name = "Release_Date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Column(name = "Comment")
    private String comment;

    @JsonIgnore
    @Column(name = "Adding_Time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime addTime;

    public Dividend() {}

    public Dividend(int edrpou, int capitalAmount, int amount, double totalNominalValue, double nominalValue, double stateDutyPaid, LocalDate releaseDate, String comment, LocalDateTime addTime) {
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

    public LocalDate getReleaseDate() {
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

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

}

