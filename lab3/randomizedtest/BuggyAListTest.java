package randomizedtest;

import static org.junit.Assert.*;
import org.junit.Test;

public class BuggyAListTest {
    @Test
    public void testThreeSAddThreeRemove(){
        AListNoResizing testAListNoResizing = new AListNoResizing<>();
        BuggyAList testBuggyAList = new BuggyAList<>();
        testAListNoResizing.addLast(4);
        testAListNoResizing.addLast(5);
        testAListNoResizing.addLast(6);

        testBuggyAList.addLast(4);
        testBuggyAList.addLast(5);
        testBuggyAList.addLast(6);

        assertEquals(testAListNoResizing.size(), testBuggyAList.size());

        assertEquals(testAListNoResizing.removeLast(), testBuggyAList.removeLast());
        assertEquals(testAListNoResizing.removeLast(), testBuggyAList.removeLast());
        assertEquals(testAListNoResizing.removeLast(), testBuggyAList.removeLast());

    }
}