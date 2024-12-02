import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.Math;
import java.lang.String;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class AoC20244 {
    public static void main(String[] args) {
        int safeReports = 0;
        try {
            String input = Files.readString(Path.of("input3.txt"));
            for( String line : input.split("\n") ) {
                boolean badLevel = false;
                ArrayList<Integer> levels = new ArrayList<>(Arrays.stream(line.split("\\s+")).map( inputLevel -> Integer.parseInt(inputLevel)).collect(Collectors.toList()));
                
                if( isReportSafe(levels) ) {
                    safeReports++;
                }
            };
            System.out.println("safeReports: " + safeReports);
        } catch (Exception e) { 
            System.out.printf("Exception: " + e );
            throw new RuntimeException(e); 
        }
    }

    public static boolean isReportSafe( final ArrayList<Integer> levels) {
        final int badLevel = unsafeLevelIndex(levels);
        if( badLevel > -1 ){
            ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
            List<Future<Integer>> futures = new ArrayList<>();
            futures.add(executor.submit(() -> {
                ArrayList<Integer> levelsCopy = new ArrayList<>(levels);
                levelsCopy.remove(badLevel);
                return unsafeLevelIndex(levelsCopy);
            }));
            if( badLevel > 0 ) {
                futures.add(executor.submit(() -> {
                    ArrayList<Integer> levelsCopy = new ArrayList<>(levels);
                    levelsCopy.remove(badLevel-1);
                    return unsafeLevelIndex(levelsCopy);
                }));
            }
            if( badLevel > 1 ) {
                futures.add(executor.submit(() -> {
                    ArrayList<Integer> levelsCopy = new ArrayList<>(levels);
                    levelsCopy.remove(badLevel-2);
                    return unsafeLevelIndex(levelsCopy);
                }));
            }
            for( Future<Integer> future : futures ) {
                try {
                    if( future.get() < 0 ) {
                        return true;
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return false;
        }
        return true;
    }

    public static int unsafeLevelIndex( ArrayList<Integer> levels ) {
        if( levels.size() == 1 ) {
            return -1;
        }
        if( levels.get(0) == levels.get(1) ) {
            return 1;
        }
        boolean isDescending = levels.get(0) > levels.get(1);
        boolean isSafe = true;
        if( isDescending ) {
            for( int i = 1; i < levels.size(); i++ ) {
                int change = levels.get(i-1) - levels.get(i);
                if(  change < 1 || change > 3 ) {
                    return i;
                }
            }
        } else {
            for( int i = 1; i < levels.size(); i++ ) {
                int change = levels.get(i) - levels.get(i-1);
                if(  change < 1 || change > 3 ) {
                    return i;
                }
            }
        }
        return -1;
    }
}
