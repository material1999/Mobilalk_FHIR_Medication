package com.example.fhir_medication.model;

import java.util.Date;

public class BatchModel {

    private String lotNumber;
    private Date expirationDate;

    public BatchModel(String lotNumber, Date expirationDate) {
        this.lotNumber = lotNumber;
        this.expirationDate = expirationDate;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "BatchModel{" +
                "lotNumber='" + lotNumber + '\'' +
                ", expirationDate=" + expirationDate +
                '}';
    }
}