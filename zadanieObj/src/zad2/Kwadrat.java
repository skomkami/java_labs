package zad2;

public class Kwadrat {

    protected double a;

    public Kwadrat(double a) {
        this.a = a;
    }

    public double getA() {
        return a;
    }

    public void setA(double bok) {
        this.a = bok;
    }

    public double area() {
        return a*a;
    }

    public boolean isBigger(Kwadrat other) {
        return other.a > this.a;
    }
}
