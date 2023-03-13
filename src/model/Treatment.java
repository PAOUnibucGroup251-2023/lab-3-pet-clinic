package model;

import java.util.Objects;

public class Treatment {
    private String description;
    private double price;

    public Treatment(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Treatment treatment = (Treatment) o;

        return Objects.equals(description, treatment.description);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = description != null ? description.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Descriere: " + description +
                ", Pret de baza=" + price;
    }
}
