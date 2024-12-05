import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day5Problem1 {
    public static void main(String[] args) {
        try {
            int sumOfMiddlePages = 0;
            String[] input = Files.readString(Path.of("2024\\day5-input.txt")).split("\n");
            HashMap<Integer, List<Integer>> notAllowedAfter = new HashMap<>();
            int i = 0;
            for (; i < input.length && input[i].length() > 1;i++) {
                String line = input[i];
                String[] ordering = line.split("\\|");
                Integer first = Integer.parseInt(ordering[0]);
                Integer second = Integer.parseInt(ordering[1].trim());
                if (notAllowedAfter.containsKey(second)) {
                    notAllowedAfter.get(second).add(first);
                } else {
                    notAllowedAfter.put(second, new ArrayList<>(){{add(first);}});
                }
            }

            for( i++; i < input.length; i++) {
                String update = input[i];
                List<Integer> pages = Arrays.stream(update.split(",")).map(page-> Integer.parseInt(page.trim())).toList();

                System.out.println("pages: " + pages);
                List<Integer> forbidden = new ArrayList<>();
                boolean invalidOrder = false;
                for (Integer page : pages) {
                    if( forbidden.contains(page)) {
                        invalidOrder = true;
                        System.out.println("failed on page: " + page + " because " + page + " is in forbidden");
                        break;
                    }
                    if (notAllowedAfter.containsKey(page)) {
                        forbidden.addAll(notAllowedAfter.get(page));
                    }
                }
                if (!invalidOrder) {
                    sumOfMiddlePages += pages.get((pages.size()-1)/2);
                }
            }



            System.out.println("sumOfMiddlePages: " + sumOfMiddlePages );
        } catch (Exception e) {
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e);
        }
    }
}
