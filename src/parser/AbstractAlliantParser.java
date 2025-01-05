package parser;

public abstract class AbstractAlliantParser extends AbstractParser {

    @Override
    String getBankName() {
        return "Alliant";
    }

    @Override
    boolean shouldSkipLine(String[] arr) {
        String amount = getAmount(arr);
        String description = getDescription(arr);
        return amount.contains("(") && amount.contains(")") || description.startsWith("WITHDRAWAL ACH ALLIANT VISA PMT TYPE");
    }

    @Override
    String getAmount(String[] arr) {
        return arr[2].replaceFirst("\\$", "");
    }

    @Override
    String getDate(String[] arr) {
        return arr[0];
    }

    @Override
    String getDescription(String[] arr) {
        return arr[1];
    }
}
