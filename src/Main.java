import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    /**
     * Prints a message according to a given grade.
     * <p>
     * It is guaranteed that the grade is within the range [0, 100].
     *
     * @param grade The grade
     */
    public static void gradeMessage(int grade) {
        int compressedGrade = grade / 10;
        switch (compressedGrade) {
            case (10):
                System.out.println("Excellent");
                break;
            case (9):
                System.out.println("Great");
                break;
            case (8):
                System.out.println("Very Good");
                break;
            case (7):
                System.out.println("Good");
                break;
            default:
                System.out.println("OK");


        }
    }

    /**
     * Compresses a given string.
     * <p>
     * The compression process is done by replacing a sequence of identical consecutive characters
     * with that same character followed by the length of sequence.
     * <p>
     * It is guaranteed that the string contains only letters (lowercase and uppercase).
     *
     * @param stringToCompress The string to compress
     * @return The compressed version of the string
     */
    public static String compressString(String stringToCompress) {
        String compressedString = "";
        int len = stringToCompress.length();
        int count=1;
        for (int i = 0; i < len - 1; i++) {
            if (i == 0)
                compressedString += stringToCompress.charAt(i);
            if (stringToCompress.charAt(i) == stringToCompress.charAt(i + 1))
                count++;
            if (stringToCompress.charAt(i) != stringToCompress.charAt(i + 1)) {
                compressedString+=String.valueOf(count);
                count=1;
                compressedString += stringToCompress.charAt(i + 1);
            }
        }
        return compressedString;
    }

    /**
     * Decompresses a given string.
     * <p>
     * The decompression process is done by duplicating each sequence of characters
     * according to the number which appears after the sequence.
     * <p>
     * It is guaranteed that the string is a legal compressed string.
     *
     * @param compressedString The string to decompress
     * @return The decompressed string
     */

    public static String duplicatedString(String stringToDuplicate, int num) {
        String duplicated = "";
        for (int i = 0; i < num; i++)
            duplicated += stringToDuplicate;
        return duplicated;
    }

    public static String decompressString(String compressedString) {
        String decompressedString = "";
        int len = compressedString.length();
        String currentToDecompress = "";
        String duplication = "";
        for (int i = 0; i < len; i++) {
            if (compressedString.charAt(i) > '9' || compressedString.charAt(i) < '0')
                currentToDecompress += compressedString.charAt(i);
            else
                duplication += compressedString.charAt(i);
            if ((i == len - 1) ||
                    ((compressedString.charAt(i) >= '0' && compressedString.charAt(i) <= '9')
                            && (compressedString.charAt(i + 1) >= '9' || compressedString.charAt(i + 1) <= '0'))) {
                int duplicatitonNum = Integer.parseInt(String.valueOf(duplication));
                decompressedString += duplicatedString(currentToDecompress, duplicatitonNum);
                currentToDecompress = "";
                duplication = "";
            }

        }
        return decompressedString;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = args[0];
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        // Tests for part A
        int numberOfGrades = scanner.nextInt();
        for (int i = 0; i < numberOfGrades; i++) {
            int grade = scanner.nextInt();
            gradeMessage(grade);
        }

        // Tests for part B1
        int numberOfStringsToCompress = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfStringsToCompress; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            System.out.println("The compressed version of " + stringToCompress + " is " + compressedString);
        }

        // Tests for part B2
        int numberOfDecompressedStrings = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfDecompressedStrings; i++) {
            String compressedString = scanner.nextLine();
            String decompressedString = decompressString(compressedString);
            System.out.println("The decompressed version of " + compressedString + " is " + decompressedString);
        }

        // Tests for both part B1 and B2
        int numberOfCombinedTests = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numberOfCombinedTests; i++) {
            String stringToCompress = scanner.nextLine();
            String compressedString = compressString(stringToCompress);
            String decompressedString = decompressString(compressedString);
            System.out.println("decompress(compress(" + stringToCompress + ")) == " + stringToCompress + "? " + stringToCompress.equals(decompressedString));
        }
    }
}
