package lab12.code;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeValidator {

    public static void main(String[] args) {
        // Specify the maze file to validate
        String inputFileName = "mazes" + java.io.File.separator + "demoMaze.txt"; // Replace with your maze file

        validateMaze(inputFileName);
    }

    public static void validateMaze(String inputFileName) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(inputFileName));
            // Read the first line to get height and width
            String line = input.readLine();
            System.out.println("First line read: '" + line + "'");

            // Check if the line is null or empty
            if (line == null || line.trim().isEmpty()) {
                System.err.println("Error: The first line of the maze file is empty or null.");
                input.close();
                return;
            }

            String[] dimensions = line.trim().split("\\s+");
            System.out.println("Dimensions array length: " + dimensions.length);

            // Ensure that dimensions array has at least two elements
            if (dimensions.length < 2) {
                System.err.println("Error: The dimensions line does not contain both height and width.");
                input.close();
                return;
            }

            int height, width;
            try {
                height = Integer.parseInt(dimensions[0]);
                width = Integer.parseInt(dimensions[1]);
                System.out.println("Parsed height: " + height + ", width: " + width);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing dimensions: " + e.getMessage());
                input.close();
                return;
            }

            // Read and validate each maze row
            for (int i = 0; i < height; i++) {
                line = input.readLine();
                if (line == null) {
                    System.err.println("Error: Maze file has fewer rows than specified height.");
                    input.close();
                    return;
                }

                // Remove leading and trailing whitespace
                line = line.trim();

                // Get the characters as an array
                char[] chars = line.toCharArray();

                // Print the processed line and number of columns
                System.out.println("Processed line " + i + " (columns " + chars.length + "): " + line);

                if (chars.length != width) {
                    System.err.println("Error: Maze row " + i + " does not contain the expected number of columns.");
                    System.err.println("Expected " + width + " columns but found " + chars.length);
                    input.close();
                    return;
                }
            }

            System.out.println("Maze validation successful.");
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
