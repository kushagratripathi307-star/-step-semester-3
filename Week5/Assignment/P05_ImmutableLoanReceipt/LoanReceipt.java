package Week5.Assignment.P05_ImmutableLoanReceipt;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(String memberId, String[] bookIds) {
        this.memberId = memberId;
        this.bookIds = (bookIds == null) ? new String[0] : bookIds.clone();
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return (bookIds == null) ? new String[0] : bookIds.clone();
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        String[] updatedBookIds = getBookIds();
        if (index >= 0 && index < updatedBookIds.length) {
            updatedBookIds[index] = newId;
        }
        return new LoanReceipt(this.memberId, updatedBookIds);
    }
}
