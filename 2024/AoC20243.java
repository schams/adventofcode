import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.Math;
import java.lang.String;
import java.util.HashSet;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AoC20243 {
    public static void main(String[] args) {
        int safeReports = 0;
        try {
            String input = Files.readString(Path.of("input3.txt"));
            for( String line : input.split("\n") ) {
                ArrayList<Integer> levels = new ArrayList<>(Arrays.stream(line.split("\\s+")).map( inputLevel -> Integer.parseInt(inputLevel)).collect(Collectors.toList()));
                if( levels.size() == 1 ) {
                    safeReports++;
                    continue;
                }
                if( levels.get(0) == levels.get(1) ) {
                    continue;
                }
                boolean isDescending = levels.get(0) > levels.get(1);
                boolean isSafe = true;
                if( isDescending ) {
                    for( int i = 1; i < levels.size(); i++ ) {
                        int change = levels.get(i-1) - levels.get(i);
                        if(  change < 1 || change > 3 ) {
                            isSafe = false;
                            break;
                        }
                    }
                } else {
                    for( int i = 1; i < levels.size(); i++ ) {
                        int change = levels.get(i) - levels.get(i-1);
                        if(  change < 1 || change > 3 ) {
                            isSafe = false;
                            break;
                        }
                    }
                }
                if( isSafe ) {
                    safeReports++;
                }
            };
            System.out.println("safeReports: " + safeReports);
        } catch (Exception e) { 
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e); 
        }
    }
}
