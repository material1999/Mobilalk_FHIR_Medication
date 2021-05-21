package com.example.fhir_medication.model;

public class IngredientModel {

    private String item;
    private Boolean isActive;
    private Integer strength;

    public IngredientModel() {}

    public IngredientModel(String item, Boolean isActive, Integer strength) {
        this.item = item;
        this.isActive = isActive;
        this.strength = strength;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "IngredientModel{" +
                "item='" + item + '\'' +
                ", isActive=" + isActive +
                ", strength=" + strength +
                '}';
    }
}
