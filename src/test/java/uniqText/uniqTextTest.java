package uniqText;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class uniqTextTest {
    @Test
    public void makeUniq() {
        List<String> testLines = new ArrayList<String>();
        testLines.add("Hello");
        testLines.add("world");
        testLines.add("wOrLd");
        UniqText testSubject = new UniqText(testLines);
        testSubject.makeTextUniq(true, true, false, 0);
        List<String> resultLines = new ArrayList<String>();
        resultLines.add("Hello");
        assertEquals(testSubject.getArray(), resultLines);
        testLines.add("WORLD");
        testSubject = new UniqText(testLines);
        testSubject.makeTextUniq(true, false, false, 0);
        resultLines.add("world");
        assertEquals(testSubject.getArray(), resultLines);
        testLines.clear();
        testLines.add("sss");
        testLines.add("sss");
        testLines.add("sss");
        testLines.add("wood");
        testSubject = new UniqText(testLines);
        resultLines.clear();
        resultLines.add("3sss");
        resultLines.add("wood");
        testSubject.makeTextUniq(false, false, true, 0);
        assertEquals(testSubject.getArray(), resultLines);
        testLines.clear();
        testLines.add("abbCdnd");
        testLines.add("baacDnD");
        testLines.add("ssscdnd");
        testLines.add("eeee");
        testLines.add("daacdnd");
        testSubject = new UniqText(testLines);
        resultLines.clear();
        resultLines.add("abbCdnd");
        resultLines.add("eeee");
        resultLines.add("daacdnd");
        testSubject.makeTextUniq(true, false, false, 3);
        assertEquals(testSubject.getArray(), resultLines);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeUniqWithEx() {
        List<String> testLines = new ArrayList<String>();
        testLines.add("e");
        testLines.add("eeeeee");
        UniqText testSubject = new UniqText(testLines);
        testSubject.makeTextUniq(false, false, false, 4);
    }
}