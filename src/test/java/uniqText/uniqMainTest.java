package uniqText;

import org.junit.Test;

import java.io.*;

import com.google.common.io.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class uniqMainTest {
    @Test
    public void main() {

        //Ввод с консоли, вывод на консоль

        System.setIn(new ByteArrayInputStream("-i -c\nAsd\naSd\naSD\nded\nend".getBytes()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        uniq.main(new String[]{""});
        System.out.flush();
        System.setOut(old);
        String[] blackResults = baos.toString().split("\n");
        String[] finalResults = new String[blackResults.length - 1];
        System.arraycopy(blackResults, 1, finalResults, 0, blackResults.length - 1);
        assertTrue(Arrays.equals(finalResults, (new String[]{"3Asd\r", "ded\r"})));

        //Ввод из файла, вывод на консоль

        File f = new File("./f.txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "utf-8"))) {
            writer.write("wORLD\n111Hello\n222hEllO\n333heLLo\nWorld");
        } catch (IOException e) {
        }
        System.setIn(new ByteArrayInputStream("-s=3 -i -u f.txt".getBytes()));
        baos = new ByteArrayOutputStream();
        old = System.out;
        ps = new PrintStream(baos);
        System.setOut(ps);
        uniq.main(new String[]{""});
        System.out.flush();
        System.setOut(old);
        finalResults = baos.toString().split("\n");
        assertTrue(Arrays.equals(finalResults, new String[]{"wORLD\r", "World\r"}));
        f.delete();

        //Ввод с консоли, вывод в файл

        System.setIn(new ByteArrayInputStream("-s=2 -o=out.txt -c\n12aabbcc\n23aabbcc\n3aaabbcc\naabbcc\nend".getBytes()));
        baos = new ByteArrayOutputStream();
        old = System.out;
        ps = new PrintStream(baos);
        System.setOut(ps);
        uniq.main(new String[]{""});
        System.out.flush();
        System.setOut(old);
        File testOut = new File("./testOut.txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(testOut), "utf-8"))) {
            writer.write("312aabbcc\naabbcc\n");
        } catch (IOException e) {
        }
        try {
            assertTrue(Files.equal(testOut, new File("./out.txt")));
        } catch (Exception e) {
            assertTrue(false);
        }
        testOut.delete();

        //Ввод из файла, вывод в файл

        f = new File("./f.txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "utf-8"))) {
            writer.write("WoW22\nwOw22\nOwo44\nWoW22\nWow22\nwow22");
        } catch (IOException e) {
        }
        System.setIn(new ByteArrayInputStream("-c -o=out.txt -i f.txt".getBytes()));
        uniq.main(new String[]{""});
        testOut = new File("./testOut.txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(testOut), "utf-8"))) {
            writer.write("2WoW22\nOwo44\n3WoW22\n");
        } catch (IOException e) {
        }
        try {
            assertTrue(Files.equal(testOut, new File("./out.txt")));
        } catch (Exception e) {
            assertTrue(false);
        }
        testOut.delete();
    }
}