import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
//
public class StatisticsOfGrades {
    /** @param  grades
     * @param  studentTotal
     *
     */
    public static void GradeStatsCalc(Integer[] grades, int studentTotal) {
        var formatter = DecimalFormat.getInstance();
        /*
        the caller of this method  has to provide an array[grades] and a corresponding size(student total)
        the if condition is to check and be sure the caller does not cause an ArrayOutOfBoundsException
         by passing an array of elements that exceeds the corresponding size passed.
         */
        if ((grades.length) == studentTotal) {
            var max = Arrays.stream(grades).max(Integer::compareTo);
            var min = Arrays.stream(grades).min(Integer::compareTo);
            var avg = Arrays.stream(grades).mapToDouble(Integer::doubleValue).average();
            var res = Arrays.asList("Maximum Grade:" + max.orElse(0) + "\n", "Minimum Grade:" + min.orElse(0) + "\n", "Average Grade:" + formatter.format(avg.orElse(0)));
            ArrayList<Integer> Buffer = new ArrayList<>(Arrays.asList(grades));
            int[] stats = new int[5];
            stats[4] = (int) Buffer.stream().filter((grade) -> grade > 80).count();
            stats[3] = (int) Buffer.stream().filter((grade) -> grade > 60 && grade <= 80).count();
            stats[2] = (int) Buffer.stream().filter((grade) -> grade > 40 && grade <= 60).count();
            stats[1] = (int) Buffer.stream().filter((grade) -> grade > 20 && grade <= 40).count();
            stats[0] = (int) Buffer.stream().filter((grade) -> grade >= 0 && grade <= 20).count();
            String  finalResult = String.join("", res);
            System.out.println(finalResult);
             System.out.println("Graph :");
            int maxBarHeight = Arrays.stream(stats).max().orElse(0);
            /*
              The idea is to  print a  vertical bar that corresponds
               to the value of largest count in the stats[] array.
             The (maxBarHeight) is the highest count from the stats[] array.
               For example, if the largest count in stats[] is 6, then maxBarHeight is 6.
              The i variable controls the current "level" or "row" of the bar graph we’re printing.
              In the first iteration, i = maxBarHeight ( 6>).
             */
            for (int i = maxBarHeight; i >= 1; i--) {
                System.out.print("   " + i + "  > ");
                for (int stat : stats) {
                    /*
                      " Now in this inner loop  we iterate over the  stats array
                      which contains for instance [23564] and compare it to our row or current level(i).
                       for each of the elements in  [23564], if it  is greater than or equal
                       to the current  level (i) , add a "#" or add empty spaces otherwise.
                       For example if we begin at ith row level  and compare it
                       to the  [23564] , we get an out like this in the
                       first iteration:  i=6
                       2    3   5   6    4
                                   ###

                        second iteration: i=5
                         2    3   5   6   4
                                      ###
                                 ###  ###

                          and so on..
                     */
                    if (stat >= i) {
                        System.out.print("#######   ");
                    } else {
                        System.out.print("          ");
                    }
                }
                // move to the next line to print to begin printing  "#" for the next row or ith level ;
                System.out.println();
            }
            System.out.println("""
                          +----------+---------+---------+---------+---------+
                          I    0-20   I  21-40  I  41-60  I  61-80  I  81-100 I
                    
                    """);
        } else {
            throw new GradesExceedGradesCountException("total number of grades cannot exceed the Student total");
        }


    }
}
