package util;

public class CSVUtil {

    public static String updateFileName(String fileName) {

        String withoutExtension = fileName.substring(0, fileName.lastIndexOf("."));

        return withoutExtension + "_CLEANED.csv";
    }

    public static String convertToCSV(String[] data) {
        return String.join(",", data);
    }

}
