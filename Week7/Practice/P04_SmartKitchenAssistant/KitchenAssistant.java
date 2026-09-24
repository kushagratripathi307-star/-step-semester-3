package Week7.Practice.P04_SmartKitchenAssistant;

import java.util.Scanner;

abstract class KitchenTool {
    private int speedLevel;

    public KitchenTool() {
        this.speedLevel = 1;
    }

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int speedLevel) {
        if (speedLevel >= 1 && speedLevel <= 5) {
            this.speedLevel = speedLevel;
        } else {
            System.out.println("rejected, speed level stays " + this.speedLevel);
        }
    }

    public abstract String prepare();
}

interface Washable {
    String clean();
}

class Blender extends KitchenTool implements Washable {

    public Blender() {
        super();
    }

    @Override
    public String prepare() {
        return "Blending at speed " + getSpeedLevel();
    }

    @Override
    public String clean() {
        return "Blender rinsed and dried";
    }
}

public class KitchenAssistant {

    public static void main(String[] args) {
        System.out.println("=== Problem 4: Smart Kitchen Assistant ===");

        Blender b = new Blender();
        b.setSpeedLevel(3);
        System.out.println("Blender speed level: " + b.getSpeedLevel());

        System.out.print("Setting speed to 9: ");
        b.setSpeedLevel(9);
        System.out.println("Current speed level: " + b.getSpeedLevel());

        System.out.println("prepare(): " + b.prepare());
        System.out.println("clean(): " + b.clean());

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Speed Level (1-5): ");
                int spd = Integer.parseInt(scanner.nextLine());
                b.setSpeedLevel(spd);
                System.out.println("Result: " + b.prepare());
            }
        }
    }
}
