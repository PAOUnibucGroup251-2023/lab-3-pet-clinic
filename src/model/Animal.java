package model;

import java.util.Arrays;

public abstract class Animal {
    private String name;

    protected Treatment[] treatments;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTreatment(Treatment treatment){
        if (treatments == null){
            treatments = new Treatment[1];
            treatments[0] = treatment;
        } else {
            treatments = Arrays.copyOf(treatments, treatments.length + 1);
            treatments[treatments.length - 1] = treatment;
        }
    }

    public abstract double calculateBillTotal();

    protected String showListOfTreatments(){
         if (treatments == null || treatments.length == 0) return "";
         else {
             StringBuffer result = new StringBuffer();
             result.append("\nTratamente efectuate:\n");
             for (Treatment t : treatments){
                 result.append(t.toString() + "\n");
             }
             return result.toString();
         }
    }

    public void displayAllDetailsToConsole(){
        System.out.println(toString() + "\n" +
                showListOfTreatments() + "\n" +
                "Total: " + calculateBillTotal());
    }

    @Override
    public abstract String toString();
}
