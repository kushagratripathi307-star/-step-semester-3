package Week7.Practice.P05_PackageDropOffLog;

import java.util.Scanner;

abstract class DeliveryNote {
    private String trackingId;

    public DeliveryNote(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        return confirmDelivery() + ", signed by " + signature;
    }
}

class ParcelNote extends DeliveryNote {
    public ParcelNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + getTrackingId() + " delivered";
    }
}

class LetterNote extends DeliveryNote {
    public LetterNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + getTrackingId() + " delivered";
    }
}

public class PackageDropOffLog {

    public static void logAll(DeliveryNote[] notes) {
        if (notes != null) {
            for (DeliveryNote note : notes) {
                if (note != null) {
                    System.out.println(note.confirmDelivery());
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: Package Drop-Off Log ===");

        ParcelNote p = new ParcelNote("TRK-1");
        System.out.println("confirmDelivery(): " + p.confirmDelivery());
        System.out.println("confirmDelivery(signature): " + p.confirmDelivery("J. Smith"));

        System.out.println("\n--- logAll Output ---");
        DeliveryNote[] batch = {
            p,
            new LetterNote("TRK-2")
        };
        logAll(batch);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Parcel Tracking ID: ");
                String trk = scanner.nextLine();
                System.out.print("Enter Signature: ");
                String sig = scanner.nextLine();
                ParcelNote userP = new ParcelNote(trk);
                System.out.println(userP.confirmDelivery(sig));
            }
        }
    }
}
