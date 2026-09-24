package Week7.Assignment.P02_GalleryDescriptionCards;

import java.util.Scanner;

abstract class ArtPiece {
    private static int counter = 1000;
    private final String pieceId;
    private String title;

    public ArtPiece(String title) {
        counter++;
        this.pieceId = "ART-" + counter;
        this.title = title;
    }

    public String getPieceId() {
        return pieceId;
    }

    public String getTitle() {
        return title;
    }

    public abstract String describe();
}

class Painting extends ArtPiece {
    public Painting(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Painting: " + getTitle() + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    public Sculpture(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Sculpture: " + getTitle() + ", carved from stone";
    }
}

public class GalleryApp {

    public static void main(String[] args) {
        System.out.println("=== Problem 2: Gallery Description Cards ===");

        Painting p = new Painting("Sunset Fields");
        Sculpture s = new Sculpture("The Thinker II");

        System.out.println("Painting Description: " + p.describe());
        System.out.println("Painting Piece ID: " + p.getPieceId());

        System.out.println("Sculpture Description: " + s.describe());
        System.out.println("Sculpture Piece ID: " + s.getPieceId());

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Painting Title: ");
                String title = scanner.nextLine();
                Painting userP = new Painting(title);
                System.out.println("Created [" + userP.getPieceId() + "]: " + userP.describe());
            }
        }
    }
}
