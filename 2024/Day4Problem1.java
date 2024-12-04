import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4Problem1 {
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
                    System.out.println("input["+i+"].charAt("+j+")" + input[i].charAt(j));
                    wordSearch[i][j] = input[i].charAt(j);
                }
            };
            for( int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (wordSearch[i][j] == 'X') {
                        Integer matches = gatherValidDirections(wordSearch, i, j).stream().map(direction -> {
                            StringBuilder sb = new StringBuilder();
                            direction.forEach(sb::append);
                            return sb.toString().equals("MAS") ? 1 : 0;
                        }).reduce(0, Integer::sum);
                        xmasCount += matches;
                    }
                }
                System.out.println();
            }
            System.out.println("xmasCount: " + xmasCount );
        } catch (Exception e) {
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e);
        }
    }

    private static int countMatchesAt(Character[][] wordSearch, int i, int j) {
        int matches = 0;
        if (wordSearch[i][j] == 'X') {
            gatherValidDirections(wordSearch, i, j);
        }
        return matches;
    }

    private static List<List<Character>> gatherValidDirections(Character[][] wordSearch, int i, int j) {
        List<List<Character>> directions = new ArrayList<>();
        if (i >= 3) {
            List<Character> up = new ArrayList<>();
            for (int k = 1; k < 4; k++) {
                up.add(wordSearch[i-k][j]);
            }
            directions.add(up);
            if (j >= 3) {
                List<Character> upLeft = new ArrayList<>();
                for (int k = 1; k < 4; k++) {
                    upLeft.add(wordSearch[i-k][j-k]);
                }
                directions.add(upLeft);
            }
            if (j < wordSearch.length - 3) {
                List<Character> upRight = new ArrayList<>();
                for (int k = 1; k < 4; k++) {
                    upRight.add(wordSearch[i-k][j+k]);
                }
                directions.add(upRight);
            }
        }
        if (i <= wordSearch.length - 4) {
            List<Character> down = new ArrayList<>();
            for (int k = 1; k < 4; k++) {
                down.add(wordSearch[i+k][j]);
            }
            directions.add(down);
            if (j >= 3) {
                List<Character> downLeft = new ArrayList<>();
                for (int k = 1; k < 4; k++) {
                    downLeft.add(wordSearch[i+k][j-k]);
                }
                directions.add(downLeft);
            }
            if (j < wordSearch.length - 3) {
                List<Character> downRight = new ArrayList<>();
                for (int k = 1; k < 4; k++) {
                    downRight.add(wordSearch[i+k][j+k]);
                }
                directions.add(downRight);
            }
        }
        if (j >= 3) {
            List<Character> left = new ArrayList<>();
            for (int k = 1; k < 4; k++) {
                left.add(wordSearch[i][j-k]);
            }
            directions.add(left);
        }
        if (j < wordSearch.length - 3) {
            List<Character> right = new ArrayList<>();
            for (int k = 1; k < 4; k++) {
                right.add(wordSearch[i][j+k]);
            }
            directions.add(right);
        }

        return directions;
    }
}
