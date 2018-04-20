package uniqText;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class uniq {
    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        String inputStr = str.nextLine();
        CommandLineArgument arguments = new CommandLineArgument(inputStr.split(" "));
        File input = new File("");
        List<String> lines = new ArrayList<String>();
        if (arguments.file != null) {
            try {
                lines = Files.readAllLines(Paths.get(arguments.file.getName()), StandardCharsets.UTF_8);
            } catch (Exception e) {
            }
        } else {
            System.out.println("Вводите текст, для того чтобы прекратить ввод, введите end");
            inputStr = str.nextLine();
            while (!inputStr.equalsIgnoreCase("end")) {
                lines.add(inputStr);
                inputStr = str.nextLine();
            }
        }
        UniqText text = new UniqText(lines);
        text.makeTextUniq(arguments.isI, arguments.isU, arguments.isC, arguments.N);
        if (arguments.oFile != null) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(arguments.oFile), "utf-8"))) {
                for (String line : text.getArray()) {
                    writer.write(line);
                    writer.write("\n");
                }
            } catch (IOException e) {
                System.out.println("Имя выходного фалйа было задано неверно");
            }
        } else {
            for (String line : text.getArray()) {
                System.out.println(line);
            }
        }
    }
}
