package com.netcracker.recipeproject.library;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private static final long serialVersionUID = -202870688583065197L;
    private int id;
    private String name;
    private String unit;

    public Ingredient(int id, String name, String unit) {
        this.id = id;
        this.name = name;
        this.unit = unit;
    }

    public Ingredient(String name, String unit) {
        this.id = -1;
        this.name = name;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
