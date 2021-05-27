import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpperCaseFileApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя файла с текстом:");
        String inputFilePath = scanner.nextLine();

        if (!isFileCorrect(inputFilePath)) {
            System.out.println("Файла нет или он пустой.");
            return;
        }

        writeUpperCaseFile(inputFilePath);

        System.out.println("Новый файл со строками в UpperCase: " + getNewFilePath(inputFilePath));
    }

    private static boolean isFileCorrect(String inputFilePath) {
        File file = new File(inputFilePath);

        if (!file.exists() || file.isDirectory()) {
            return false;
        }

        return file.length() != 0;
    }

    private static void writeUpperCaseFile(String inputFilePath) {
        List<String> stringsList = getStringsList(inputFilePath);

        String outputFilePath = getNewFilePath(inputFilePath);
        File outputFile = new File(outputFilePath);

        BufferedWriter bufferedWriter;

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));

            for (String s : stringsList) {
                bufferedWriter.write(s.toUpperCase());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getNewFilePath(String inputFilePath) {
        File sourceFile = new File(inputFilePath);

        return sourceFile.getName() + "_UpperCased." + getFileExtension(sourceFile);
    }

    private static List<String> getStringsList(String inputFilePath) {
        String line;

        List<String> stringsList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringsList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringsList;
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != 0 && fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return "";
        }
    }
}