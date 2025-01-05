package parser;

public class AlliantCreditParser extends AbstractAlliantParser {

    private static final String[] expectedHeaders = {"Date", "Description", "Amount", "Balance", "Post Date"};

    @Override
    String[] getExpectedHeaders() {
        return expectedHeaders;
    }

    @Override
    boolean shouldSkipLine(String[] arr) {
        String amount = getAmount(arr);
        return amount.contains("(") && amount.contains(")");
    }
}
