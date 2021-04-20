package com.netcracker.recipeproject.library;

import java.util.Comparator;

public class DishComparator implements Comparator<Dish> {
    @Override
    public int compare(Dish dish1, Dish dish2) {
        String[] parts1 = dish1.getCookingTime().split(" ");
        Integer thisTime = Integer.parseInt(parts1[0]);
        String thisTimeUnit = parts1[1];
        String[] parts2 = dish2.getCookingTime().split(" ");
        Integer anotherTime = Integer.parseInt(parts2[0]);
        String anotherTimeUnit = parts2[1];
        int result = 0;
        if(thisTimeUnit.contains("час") && anotherTimeUnit.contains("час"))
            result = 0;
        else if (thisTimeUnit.contains("час") && !anotherTimeUnit.contains("час"))
            result = 2;
        else if (!thisTimeUnit.contains("час") && anotherTimeUnit.contains("час"))
            result = -2;
        return thisTime.compareTo(anotherTime) + result;
    }


}
