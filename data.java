import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class DataLeakageDetectionSystem {

    private Set<String> sensitiveKeywords;

    public DataLeakageDetectionSystem() {
        sensitiveKeywords = new HashSet<>();
        // Add sensitive keywords that you want to detect
        sensitiveKeywords.add("password");
        sensitiveKeywords.add("confidential");
        // Add more keywords as needed
    }

    public void scanDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Invalid directory path or directory does not exist.");
            return;
        }

        scanFilesInDirectory(directory);
    }

    private void scanFilesInDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanFilesInDirectory(file);
                } else if (file.isFile()) {
                    if (isSupportedFileType(file.getName())) {
                        checkFileForSensitiveKeywords(file);
                    }
                }
            }
        }
    }

    private boolean isSupportedFileType(String filename) {
        return filename.endsWith(".txt") || filename.endsWith(".docx") || filename.endsWith(".pdf");
        // Add more file types as needed
    }

    private void checkFileForSensitiveKeywords(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                for (String keyword : sensitiveKeywords) {
                    if (line.toLowerCase().contains(keyword)) {
                        System.out.println("Found sensitive keyword '" + keyword + "' in file: " + file.getAbsolutePath() + " at line " + lineNumber);
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        DataLeakageDetectionSystem detector = new DataLeakageDetectionSystem();
        // Replace "path/to/your/directory" with the directory you want to scan
        detector.scanDirectory("path/to/your/directory");
    }
}
