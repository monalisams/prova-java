package br.com.confidencecambio.javabasico.dto.imc;


public class Imc {
    private double weight;
    private double height;

    public Imc(double weight, double height) {
        this.setWeight(weight);
        this.setHeight(height);
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}