package com.netcracker.recipeproject.library;

import java.io.Serializable;
import java.util.Objects;

public class Ingredient implements Serializable, Comparable<Ingredient> {
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

    public Ingredient() {
        id = -1;
        name = "";
        unit = "";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return name.equalsIgnoreCase(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Ingredient o) {
        String thisName = this.getName();
        String anotherName = o.getName();
        return thisName.compareToIgnoreCase(anotherName);
    }
    //подумать нужен ли метод
}
