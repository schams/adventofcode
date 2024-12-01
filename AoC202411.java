import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.Math;
import java.lang.String;

public class AoC202411 {
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

            left.sort(null);
            right.sort(null);

            int totalDistance = 0;
            for( int index = 0; index < left.size(); index++ ) {
                totalDistance += Math.abs(left.get(index) - right.get(index));
            }
        } catch (Exception e) { 
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e); 
        }
    }
}
