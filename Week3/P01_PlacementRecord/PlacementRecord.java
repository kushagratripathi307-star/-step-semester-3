package Week3.P01_PlacementRecord;

public class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;

    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    public void printRecord() {
        System.out.printf("%s -> %s @ %.1f LPA\n", studentName, company, packageLpa);
    }

    public static void main(String[] args) {
        PlacementRecord[] records = new PlacementRecord[3];
        records[0] = new PlacementRecord("Ravi", "TCS", 4.5);
        records[1] = new PlacementRecord("Anitha", "Zoho", 6.2);
        records[2] = new PlacementRecord("Karthik", "Infosys", 4.0);

        for (PlacementRecord record : records) {
            record.printRecord();
        }
    }
}
