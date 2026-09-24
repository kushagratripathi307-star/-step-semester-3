package Week7.Practice.P03_OrchestraWarmUpRoutine;

import java.util.Scanner;

abstract class Instrument {
    public abstract String play();
}

class StringInstrument extends Instrument {
    public StringInstrument() {
        super();
    }

    @Override
    public String play() {
        return "Strumming the strings";
    }
}

class Violin extends StringInstrument {
    public Violin() {
        super();
    }

    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}

public class OrchestraRoutine {

    public static void main(String[] args) {
        System.out.println("=== Problem 3: Orchestra Warm-Up Routine ===");

        StringInstrument s = new StringInstrument();
        System.out.println("StringInstrument play(): " + s.play());

        Violin v = new Violin();
        System.out.println("Violin play(): " + v.play());

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                Instrument inst = new Violin();
                System.out.println("Polymorphic Violin play(): " + inst.play());
            }
        }
    }
}
