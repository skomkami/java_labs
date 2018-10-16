package zad3;

import zad2.Kwadrat;

public class Prostokat extends Kwadrat {

    protected double b;

    public Prostokat(double a, double b) {
        super(a);
        this.b = b;
    }

    public double area() {
        return this.a * this.b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getB() {
        return b;
    }

    public boolean isBigger(Prostokat other) {
        return other.a * other.b > this.a * this.b;
    }
}
