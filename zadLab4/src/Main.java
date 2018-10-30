import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if(args.length != 2){
            System.out.println("Niepoprawna ilość argumentów");
            System.exit(0);
        }

        System.out.println("Wybierz tryb :");
        System.out.println("1. Szyfrowanie");
        System.out.println("2. Deszyfrowanie");
        System.out.print("Twój wybór: ");
        int choice = App.getInt();
        System.out.println(choice);

        if(choice != 1 && choice != 2) {
            System.out.println("Niepoprawny wybór");
            System.exit(0);
        }

        System.out.println("Jakim algorytmem chcesz szyfrować :");
        System.out.println("1. ROT11");
        System.out.println("2. Polibiusz");
        System.out.print("Twój wybór: ");
        int choice2 = App.getInt();

        Algorithm algo = new ROT11();

        switch(choice2) {
            case 1:
                break;
            case 2: algo = new Polibiusz();
                break;
            default:
                System.out.println("Niepoprawny wybór!!");
                System.exit(0);
                break;
        }

        File inFile = new File("./" + args[0]);
        File outFile = new File("./" + args[1]);

        try {
            switch (choice) {
                case 1:
                    Cryptographer.cryptfile(inFile, outFile, algo);
                    break;
                case 2:
                    Cryptographer.decryptfile(inFile, outFile, algo);
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
