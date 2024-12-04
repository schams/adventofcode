import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day4Problem2 {
    public static void main(String[] args) {
        try {
            String[] input = Files.readString(Path.of("2024\\input5.txt")).split("\n");
            int width = input[0].strip().length();
            int height = input.length;
            int xmasCount = 0;
            Character[][] wordSearch = new Character[height][width];
            for (int i = 0; i < height; i++) {
                System.out.println(input[i]);
                for (int j = 0; j < width; j++) {
                    wordSearch[i][j] = input[i].charAt(j);
                }
            };
            for( int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (wordSearch[i][j] == 'A' && i >= 1 && j >= 1 && i <= height - 2 && j <= width - 2) {
                        if ( "MS".replace("" +wordSearch[i-1][j-1], "").replace(""+wordSearch[i+1][j+1], "").length() == 0
                                && "MS".replace("" +wordSearch[i+1][j-1], "").replace(""+wordSearch[i-1][j+1], "").length() == 0) {
                            xmasCount++;
                        }
                    }
                }
            }
            System.out.println("xmasCount: " + xmasCount );
        } catch (Exception e) {
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e);
        }
    }
}
