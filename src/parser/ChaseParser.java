package parser;

public class ChaseParser extends AbstractParser {

    private static final String[] expectedHeaders = {"Transaction Date", "Post Date", "Description", "Category", "Type", "Amount", "Memo"};

    @Override
    String getBankName() {
        return "Chase";
    }

    @Override
    String[] getExpectedHeaders() {
        return expectedHeaders;
    }

    @Override
    boolean shouldSkipLine(String[] arr) {
        return Float.parseFloat(getAmount(arr)) > 0;
    }

    @Override
    String getAmount(String[] arr) {
        return arr[5];
    }

    @Override
    String getDate(String[] arr) {
        return arr[0];
    }

    @Override
    String getDescription(String[] arr) {
        return arr[2];
    }
}
