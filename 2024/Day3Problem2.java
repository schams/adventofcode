import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Problem2 {
    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("2024\\input4.txt"));
            Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");
            System.out.println( "eh " + input);

            Matcher matcher = pattern.matcher(input);
            int total = 0;
            boolean enabled = true;
            while (matcher.find()) {
                String method = matcher.group(0);
                if (method.contains("don't")) {
                    enabled = false;
                } else if (method.contains("do")) {
                    enabled = true;
                } else if (enabled) {
                    int a = Integer.parseInt(matcher.group(1));
                    int b = Integer.parseInt(matcher.group(2));
                    total += a * b;

                }
                /*int a = Integer.parseInt(matcher.group(1));
                int b = Integer.parseInt(matcher.group(2));
                total += a * b;*/
            }
            System.out.println("total: " + total );
        } catch (Exception e) {
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e);
        }
    }
}
