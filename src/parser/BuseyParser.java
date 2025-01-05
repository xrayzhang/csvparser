package parser;
public class BuseyParser extends AbstractParser {

    private static final String[] expectedHeaders = {"Account Name", "Processed Date", "Description", "Check Number", "Credit or Debit", "Amount"};
    private static final String CREDIT = "\"Credit\"";
    private static final String VENMO = "\"VENMO";

    @Override
    String getBankName() {
        return "Busey";
    }

    @Override
    String[] getExpectedHeaders() {
        return expectedHeaders;
    }

    @Override
    boolean shouldSkipLine(String[] arr) {
        String creditOrDebit = arr[4];
        String description = arr[2];

        return creditOrDebit.equals(CREDIT) || description.startsWith(VENMO);
    }

    @Override
    String getAmount(String[] arr) {
        return arr[5];
    }

    @Override
    String getDate(String[] arr) {
        return arr[1];
    }

    @Override
    String getDescription(String[] arr) {
        return arr[2].replaceFirst("(POS DEB|DBT CRD) CARD# \\d{4} [01]\\d/[0123]\\d{1}/\\d{2} \\d{4} ", "");
    }

}
