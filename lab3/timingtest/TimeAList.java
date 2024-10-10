package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList testList = new AList<Integer>();
        AList n = new AList<Integer>();
        AList times = new AList<Double>();
        AList ops = new AList<Integer>();
        int cnt = 0;
        double timeInSeconds;
        Stopwatch sw = new Stopwatch();

        for(int i = 1; i <= 128000; i++) {
            testList.addLast(i);
            cnt ++;
            timeInSeconds = sw.elapsedTime();
            if(i == 1000 || i == 2000 || i == 4000 || i == 8000 || i == 16000 || i == 32000 || i == 64000 || i == 128000){
                n.addLast(i);
                ops.addLast(cnt);
                times.addLast(timeInSeconds);

            }
        }
        printTimingTable(n, times, ops);
    }
}