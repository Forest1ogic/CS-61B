package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class RandomizedTest {
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                L.size();
                BL.size();
            } else if (operationNumber == 2 && L.size() > 0) {
                // getLast
                L.getLast();
                BL.getLast();
            } else if (operationNumber == 3 && L.size() > 0) {
                // removeLast
                L.removeLast();
                BL.removeLast();
            }
        }
    }
}
