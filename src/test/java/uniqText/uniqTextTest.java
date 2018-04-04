package uniqText;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class uniqTextTest {
    @Test
    public void mkUniq() {
        List<String> testLines = new ArrayList<String>();
        testLines.add("Hello");
        testLines.add("world");
        testLines.add("wOrLd");
        uniqText testSubject = new uniqText(testLines);
        testSubject.mkUniqText("-i -u");
        List<String> resultLines = new ArrayList<String>();
        resultLines.add("Hello");
        assertEquals(testSubject.getArray(), resultLines);
        try {
            testSubject.mkUniqText("-g");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка 1 поймана");
        }
        try {
            testSubject.mkUniqText("-i -u -s");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка 2 поймана");
        }
        try {
            testSubject.mkUniqText("-i -u -c 9");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка 3 поймана");
        }
        testLines.add("WORLD");
        testSubject = new uniqText(testLines);
        testSubject.mkUniqText("-i");
        resultLines.add("WORLD");
        assertEquals(testSubject.getArray(), resultLines);
        testLines.clear();
        testLines.add("sss");
        testLines.add("sss");
        testLines.add("sss");
        testLines.add("wood");
        testSubject = new uniqText(testLines);
        resultLines.clear();
        resultLines.add("3sss");
        resultLines.add("wood");
        testSubject.mkUniqText("-c");
        assertEquals(testSubject.getArray(), resultLines);
        testLines.clear();
        testLines.add("abbCdnd");
        testLines.add("baacDnD");
        testLines.add("ssscdnd");
        testLines.add("eeee");
        testLines.add("daacdnd");
        testSubject = new uniqText(testLines);
        resultLines.clear();
        resultLines.add("ssscdnd");
        resultLines.add("eeee");
        resultLines.add("daacdnd");
        testSubject.mkUniqText("-i -s 3");
        assertEquals(testSubject.getArray(), resultLines);
        testLines.clear();
        testLines.add("e");
        testLines.add("eeeeee");
        testSubject = new uniqText(testLines);
        try {
            testSubject.mkUniqText("-s 4");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка 4 поймана");
        }
    }
}