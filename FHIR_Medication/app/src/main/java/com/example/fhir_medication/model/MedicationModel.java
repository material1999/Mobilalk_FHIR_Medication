package com.example.fhir_medication.model;

import java.util.ArrayList;

public class MedicationModel {

    private String id;
    private ArrayList<String> identifier;
    private String code;
    private String status;
    private String manufacturer;
    private String form;
    private Integer amount;
    private ArrayList<IngredientModel> ingredient;
    private BatchModel batch;
    private Integer imageResource;

    public MedicationModel() {}

    public MedicationModel(ArrayList<String> identifier, String code, String status,
                           String manufacturer, String form, Integer amount,
                           ArrayList<IngredientModel> ingredient,
                           BatchModel batch, Integer imageResource) {
        this.identifier = identifier;
        this.code = code;
        this.status = status;
        this.manufacturer = manufacturer;
        this.form = form;
        this.amount = amount;
        this.ingredient = ingredient;
        this.batch = batch;
        this.imageResource = imageResource;
    }

    public ArrayList<String> getIdentifier() {
        return identifier;
    }

    public void setIdentifier(ArrayList<String> identifier) {
        this.identifier = identifier;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ArrayList<IngredientModel> getIngredient() {
        return ingredient;
    }

    public void setIngredient(ArrayList<IngredientModel> ingredient) {
        this.ingredient = ingredient;
    }

    public BatchModel getBatch() {
        return batch;
    }

    public void setBatch(BatchModel batch) {
        this.batch = batch;
    }

    public Integer getImageResource() {
        return imageResource;
    }

    public void setImageResource(Integer imageResource) {
        this.imageResource = imageResource;
    }

    public String _getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
