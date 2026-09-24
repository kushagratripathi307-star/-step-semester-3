package Week7.Practice.P01_TalkingToyBox;

import java.util.Scanner;

abstract class Toy {
    private static int counter = 1000;
    private final String toyId;
    private String name;

    public Toy(String name) {
        counter++;
        this.toyId = "TOY-" + counter;
        this.name = name;
    }

    public String getToyId() {
        return toyId;
    }

    public String getName() {
        return name;
    }

    public abstract String makeSound();
}

class ToyCar extends Toy {
    public ToyCar(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return getName() + ": Vroom vroom!";
    }
}

class ToyRobot extends Toy {
    public ToyRobot(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return getName() + ": Beep boop!";
    }
}

public class TalkingToyBox {

    public static void main(String[] args) {
        System.out.println("=== Problem 1: The Talking Toy Box ===");

        ToyCar c = new ToyCar("Speedster");
        ToyRobot r = new ToyRobot("Bolt");

        System.out.println("ToyCar makeSound(): " + c.makeSound());
        System.out.println("ToyRobot makeSound(): " + r.makeSound());

        System.out.println("ToyCar toyId: " + c.getToyId());
        System.out.println("ToyRobot toyId: " + r.getToyId());

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Toy Car Name: ");
                String name = scanner.nextLine();
                ToyCar userCar = new ToyCar(name);
                System.out.println("Created [" + userCar.getToyId() + "]: " + userCar.makeSound());
            }
        }
    }
}
