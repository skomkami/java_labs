package zad4;

import zad3.Prostokat;

import java.util.LinkedList;

import java.util.Scanner;

public class Test {

    public LinkedList<Prostokat> figury = new LinkedList<>();

    public void menu() {
        System.out.println("1. Dodaj prostokat");
        System.out.println("2. Wyświetl wszystkie prostokaty");
        System.out.println("3. Oblicz sume pol wszystkich prostokatow");
        System.out.println("4. Zakoncz");

        System.out.print("Twoj wybor: ");
    }

    public void dodajProstokat() {

        Scanner in = new Scanner(System.in);

        System.out.print("Podaj bok a: ");

        double a, b;

        a = in.nextDouble();

        System.out.print("\nPodaj bok b: ");

        b = in.nextDouble();

        this.figury.add(new Prostokat(a,b));
    }

    public void wyswietlProstokaty() {

        for(int i=0; i<figury.size(); ++i)
        {
            System.out.println("Prostokat nr " + (i+1) + ": ");
            System.out.println("Bok a: " + figury.get(i).getA() );
            System.out.println("Bok b: " + figury.get(i).getB() + "\n----------------------------\n");
        }
    }

    public void wyswietlPola() {

        System.out.println( "-----------------");

        for(int i=0; i<figury.size(); ++i)
        {
            System.out.println("Prostokat nr " + (i+1) + " ma pole: " + figury.get(i).area());
        }

        System.out.println( "-----------------");

    }

    public static void main(String[] args) {

        Test test = new Test();

        Scanner in = new Scanner(System.in);

        boolean run = true;

        int num;

        while(run) {
            test.menu();

            num = in.nextInt();

            switch (num) {
                case 1:
                    test.dodajProstokat();
                    break;
                case 2:
                    test.wyswietlProstokaty();
                    break;
                case 3:
                    test.wyswietlPola();
                    break;
                case 4: run = false;
                    break;
                default:
                    System.out.println("Zly wybor");

            }

        }

    }
}
