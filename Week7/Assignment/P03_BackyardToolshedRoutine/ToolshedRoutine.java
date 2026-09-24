package Week7.Assignment.P03_BackyardToolshedRoutine;

import java.util.Scanner;

abstract class GardenTool {
    public abstract String use();
}

class CuttingTool extends GardenTool {
    public CuttingTool() {
        super();
    }

    @Override
    public String use() {
        return "Using the tool in the garden, blade sharpened first";
    }
}

class Pruner extends CuttingTool {
    public Pruner() {
        super();
    }

    @Override
    public String use() {
        return super.use() + ", then trimming branches precisely";
    }
}

public class ToolshedRoutine {

    public static void main(String[] args) {
        System.out.println("=== Problem 3: Backyard Toolshed Routine ===");

        CuttingTool c = new CuttingTool();
        System.out.println("CuttingTool use(): " + c.use());

        Pruner p = new Pruner();
        System.out.println("Pruner use(): " + p.use());

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                GardenTool tool = new Pruner();
                System.out.println("Polymorphic GardenTool (Pruner) use(): " + tool.use());
            }
        }
    }
}
