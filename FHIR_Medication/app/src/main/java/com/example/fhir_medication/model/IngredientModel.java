package com.example.fhir_medication.model;

public class IngredientModel {

    private String item;
    private boolean isActive;
    private int strength;

    public IngredientModel(String item, boolean isActive, int strength) {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
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
