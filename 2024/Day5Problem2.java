import java.nio.file.Files;
import java.nio.file.Path;

public class Day5Problem2 {
    public static void main(String[] args) {
        try {
            String[] input = Files.readString(Path.of("2024\\input5.txt")).split("\n");



            //System.out.println("xmasCount: " + xmasCount );
        } catch (Exception e) {
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e);
        }
    }
}
