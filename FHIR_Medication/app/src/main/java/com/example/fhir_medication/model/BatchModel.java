package com.example.fhir_medication.model;

import java.io.Serializable;
import java.util.Date;

public class BatchModel implements Serializable {

    private String lotNumber;
    private Date expirationDate;

    public BatchModel() {}

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
        return "{" +
                "lotNumber='" + lotNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
