package com.netcracker.recipeproject.server.model;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int[] a = {1,2,3};
        int[] b = {1,2,3,4};
        boolean test=true;
        //массивы просто для примера чтоб их имена использовать
        for (int i = 0; i < a.length; i++){
            int finalI = i;
            if(!(IntStream.of(b).anyMatch(x -> x == a[finalI]))){
                test = false;
                System.out.println("Содержит не все элементы");
                break;}
        }
    }
}
