import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Problem2 {
    public enum TokenType {
        MUL ("mul\\((\\d{1,3}),(\\d{1,3})\\)"),
        DONT ("don't\\(\\)"),
        DO ("do\\(\\)");

        public final String pattern;

        TokenType(String pattern) {
            this.pattern = pattern;
        }
    }
    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("2024\\input4.txt"));
            StringBuilder combinedTokenPatterns = new StringBuilder();
            for (TokenType tokenType : TokenType.values()) {
                combinedTokenPatterns.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
            }
            Pattern pattern = Pattern.compile(combinedTokenPatterns.substring(1));
            int total = getTotal(pattern, input);
            System.out.println("total: " + total );
        } catch (Exception e) {
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e);
        }
    }

    private static int getTotal(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        int total = 0;
        boolean enabled = true;

        while (matcher.find()) {
            if (matcher.group(TokenType.DONT.name()) != null) {
                enabled = false;
            } else if (matcher.group(TokenType.DO.name()) != null) {
                enabled = true;
            } else if (enabled && matcher.group(TokenType.MUL.name()) != null) {
                int a = Integer.parseInt(matcher.group(2));
                int b = Integer.parseInt(matcher.group(3));
                total += a * b;
            }
        }
        return total;
    }
}
