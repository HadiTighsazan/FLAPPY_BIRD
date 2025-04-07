package src.model;

import java.io.*;

public class HighScoreManager {
    private static final String FILE_NAME = "highscore.txt";

    public static int readHighScore() {
        int highScore = 0;
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("0");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return highScore;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                highScore = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return highScore;
    }

    public static void writeHighScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateHighScore(int currentScore) {
        int highScore = readHighScore();
        if (currentScore > highScore) {
            writeHighScore(currentScore);
        }
    }
}
