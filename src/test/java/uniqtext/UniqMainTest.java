package uniqtext;

import org.junit.Test;

import java.io.*;

import com.google.common.io.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class UniqMainTest {
    @Test
    public void main() throws IOException {

        //Ввод с консоли, вывод на консоль

        System.setIn(new ByteArrayInputStream("Asd\naSd\naSD\nded\nend".getBytes()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        Uniq.main(new String[]{"-i", "-c"});
        System.out.flush();
        System.setOut(old);
        String[] blackResults = baos.toString().split("\n");
        String[] finalResults = new String[blackResults.length - 1];
        System.arraycopy(blackResults, 1, finalResults, 0, blackResults.length - 1);
        assertTrue(Arrays.equals(finalResults, (new String[]{"3Asd\r", "ded\r"})));
        ps.close();

        //Ввод из файла, вывод на консоль

        File f = new File("./f.txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "utf-8"))) {
            writer.write("wORLD\n111Hello\n222hEllO\n333heLLo\nWorld");
        }
        baos = new ByteArrayOutputStream();
        old = System.out;
        ps = new PrintStream(baos);
        System.setOut(ps);
        Uniq.main(new String[]{"-s=3", "-i", "-u", "f.txt"});
        System.out.flush();
        System.setOut(old);
        finalResults = baos.toString().split("\n");
        assertTrue(Arrays.equals(finalResults, new String[]{"wORLD\r", "World\r"}));
        ps.close();
        f.delete();

        //Ввод с консоли, вывод в файл

        System.setIn(new ByteArrayInputStream("12aabbcc\n23aabbcc\n3aaabbcc\naabbcc\nend".getBytes()));
        Uniq.main(new String[]{"-s=2", "-o=out.txt", "-c"});
        File testOut = new File("./testOut.txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(testOut), "utf-8"))) {
            writer.write("312aabbcc\naabbcc\n");
        }
        assertTrue(Files.equal(testOut, new File("./out.txt")));
        testOut.delete();

        //Ввод из файла, вывод в файл

        f = new File("./f.txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(f), "utf-8"))) {
            writer.write("WoW22\nwOw22\nOwo44\nWoW22\nWow22\nwow22");
        }
        Uniq.main(new String[]{"-c", "-o=out.txt", "-i", "f.txt"});
        testOut = new File("./testOut.txt");
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(testOut), "utf-8"))) {
            writer.write("2WoW22\nOwo44\n3WoW22\n");
        }
        assertTrue(Files.equal(testOut, new File("./out.txt")));
        testOut.delete();
        f.delete();

        //Неверное имя входного файла

        baos = new ByteArrayOutputStream();
        old = System.out;
        ps = new PrintStream(baos);
        System.setOut(ps);
        Uniq.main(new String[]{"./ff"});
        System.out.flush();
        System.setOut(old);
        assertTrue(baos.toString().equals("Имя входного файла введено некорректно\r\n"));
        ps.close();

        //Отрицательный аргумент для -s

        baos = new ByteArrayOutputStream();
        old = System.out;
        ps = new PrintStream(baos);
        System.setOut(ps);
        Uniq.main(new String[]{"-s=-9"});
        System.out.flush();
        System.setOut(old);
        assertTrue(baos.toString().equals("Аргумент для -s не может быть отрицательным\r\n"));
        ps.close();

        //Одновременно -u  -c

        baos = new ByteArrayOutputStream();
        old = System.out;
        ps = new PrintStream(baos);
        System.setOut(ps);
        Uniq.main(new String[]{"-u", "-c"});
        System.out.flush();
        System.setOut(old);
        assertTrue(baos.toString().equals("option \"-c\" cannot be used with the option(s) [-u]\r\n"));
        ps.close();
    }
}