import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.Math;
import java.lang.String;
import java.util.HashSet;

public class AoC20242 {
    public static void main(String[] args) {
        ArrayList<Integer> left = new ArrayList();
        ArrayList<Integer> right = new ArrayList();
        try {
            String input = Files.readString(Path.of("input1.txt"));
            for( String line : input.split("\n") ) {
                    String[] pair = line.split("\\s+");
                    if( pair.length == 2 ) {
                        left.add(Integer.parseInt(pair[0]));
                        right.add(Integer.parseInt(pair[1]));
                    }
            };

            right.sort(null);
            int similarityScore = left.stream().map( leftNumber -> {
                int rightOccurrances = 0;
                int i=right.indexOf(leftNumber);
                if( i >= 0 ) {
                    System.out.println( leftNumber + " found in right at " + i + "= " + right.get(i));
                    while( right.get(i).equals(leftNumber) ) {
                        rightOccurrances++;
                        i++;
                    }
                }
                System.out.println( leftNumber + "has " + rightOccurrances + " in right");
                return leftNumber * rightOccurrances;
            }).reduce(0, (acc, score) -> {
                return acc + score;
            });
            System.out.println("similarityScore: " + similarityScore);
        } catch (Exception e) { 
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e); 
        }
    }
}
