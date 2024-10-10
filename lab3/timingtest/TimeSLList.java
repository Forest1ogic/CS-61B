package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        SLList testList = new SLList<Integer>();

        AList n = new AList<Integer>();
        AList times = new AList<Double>();
        AList ops = new AList<Integer>();

        for(int j = 1; j <= 128000; j++) {
            testList.addLast(1);

            if(j == 1000 || j == 2000 || j == 4000 || j == 8000 || j == 16000 || j == 32000 || j == 64000 || j == 128000) {
                n.addLast(j);
                ops.addLast(10000);
                Stopwatch sw = new Stopwatch();
                for(int cnt = 0; cnt < 10000; cnt ++) {
                    testList.getLast();
                }
                double timeInSeconds = sw.elapsedTime();
                times.addLast(timeInSeconds);
            }
        }
        printTimingTable(n, times, ops);
    }
}