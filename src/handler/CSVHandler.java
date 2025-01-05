package handler;

import gui.CSVUploader;
import parser.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSVHandler {

    static Map<CSVUploader.Bank, AbstractParser> parserMap = new HashMap<>();

    public static void parseFile(CSVUploader.Bank bank, File csvFile) {
        createParserMap();

        try {
            File file = new File(csvFile.getAbsolutePath());
            Scanner scanner = new Scanner(file);
            AbstractParser parser = parserMap.get(bank);
            if (parser == null) {
                throw new RuntimeException("No bank parser found!");
            }
            parser.parse(scanner, csvFile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createParserMap() {
        parserMap.put(CSVUploader.Bank.ALLIANT_CHECKING, new AlliantCheckingSavingParser());
        parserMap.put(CSVUploader.Bank.ALLIANT_CREDIT, new AlliantCreditParser());
        parserMap.put(CSVUploader.Bank.CHASE, new ChaseParser());
        parserMap.put(CSVUploader.Bank.BUSEY, new BuseyParser());
    }
}
