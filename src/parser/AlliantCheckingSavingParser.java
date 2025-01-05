package parser;

public class AlliantCheckingSavingParser extends AbstractAlliantParser {

    private static final String[] expectedHeaders = {"Date", "Description", "Amount", "Balance"};

    @Override
    String[] getExpectedHeaders() {
        return expectedHeaders;
    }

    @Override
    String getAmount(String[] arr) {
        return arr[2].replaceFirst("\\$", "").replaceAll("\\(", "").replaceAll("\\)", "");
    }

    @Override
    boolean shouldSkipLine(String[] arr) {
        String description = getDescription(arr);
        return description.startsWith("\"WITHDRAWAL ACH ALLIANT VISA PMT TYPE") || description.startsWith("\"DEPOSIT ONLINE BANKING TRANSFER");
    }
}
