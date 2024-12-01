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
            String input = Files.readString(Path.of("input1-0.txt"));
            for( String line : input.split("\n") ) {
                    String[] pair = line.split("\\s+");
                    if( pair.length == 2 ) {
                        left.add(Integer.parseInt(pair[0]));
                        right.add(Integer.parseInt(pair[1]));
                    }
            };

            left.sort(null);
            right.sort(null);
            int similarityScore = left.stream().map( number -> {
                int leftOccurrances = 1;
                int i=left.indexOf(number);
                while( ++i < left.size() && left.get(i) == number ) {
                    leftOccurrances++;
                }
                int rightOccurrances = 0;
                i=right.indexOf(number);
                if( i >= 0 ) {
                    while( right.get(i++) == number ) {
                        rightOccurrances++;
                    }
                }
                System.out.println( number + "has " + leftOccurrances + " in left and " + rightOccurrances + " in right");
                return leftOccurrances * rightOccurrances;
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
