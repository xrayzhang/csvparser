package parser;

import util.CSVUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractParser {

    abstract String getBankName();

    abstract String[] getExpectedHeaders();

    abstract boolean shouldSkipLine(String[] arr);

    abstract String getAmount(String[] arr);

    abstract String getDate(String[] arr);

    abstract String getDescription(String[] arr);

    public File parse(Scanner scanner, String fileName) throws FileNotFoundException {
        List<String[]> csvLines = new ArrayList<>();
        String[] headers;
        if (scanner.hasNext()) {
            headers = scanner.nextLine().replaceAll("\"", "").split(",");
            if (!Arrays.equals(headers, getExpectedHeaders())) {
                throw new RuntimeException(getBankName() + " headers did not match expected!");
            }
        }
        while (scanner.hasNext()) {
            String[] currLine = scanner.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            String description = getDescription(currLine);
            String date = getDate(currLine);
            String amount = getAmount(currLine);

            if (shouldSkipLine(currLine)) {
                continue;
            }

            String[] parsed = new String[3];
            parsed[0] = date;
            parsed[1] = description;
            parsed[2] = String.format("%.2f", Math.abs(Float.parseFloat(amount)));
            csvLines.add(parsed);
        }

        File csvOutput = new File(CSVUtil.updateFileName(fileName));
        try (PrintWriter writer = new PrintWriter(csvOutput)) {
            csvLines.stream()
                    .map(CSVUtil::convertToCSV)
                    .forEach(writer::println);
        }

        return csvOutput;
    }
}
