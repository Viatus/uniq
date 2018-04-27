package uniqText;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UniqTextTest {
    @Test
    public void makeUniq() {
        List<String> testLines = new ArrayList<String>();
        testLines.add("Hello");
        testLines.add("world");
        testLines.add("wOrLd");
        UniqText testSubject = new UniqText(testLines);
        testSubject.makeTextUniq(new CommandLineArgument(new String[]{"-i", "-u"}));
        List<String> resultLines = new ArrayList<String>();
        resultLines.add("Hello");
        assertEquals(testSubject.getLines(), resultLines);
        testLines.add("WORLD");
        testSubject = new UniqText(testLines);
        testSubject.makeTextUniq(new CommandLineArgument(new String[]{"-i"}));
        resultLines.add("world");
        assertEquals(testSubject.getLines(), resultLines);
        testLines.clear();
        testLines.add("sss");
        testLines.add("sss");
        testLines.add("sss");
        testLines.add("wood");
        testSubject = new UniqText(testLines);
        resultLines.clear();
        resultLines.add("3sss");
        resultLines.add("wood");
        testSubject.makeTextUniq(new CommandLineArgument(new String[]{"-c"}));
        assertEquals(testSubject.getLines(), resultLines);
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
        testSubject.makeTextUniq(new CommandLineArgument(new String[]{"-i", "-s=3"}));
        assertEquals(testSubject.getLines(), resultLines);
    }
}