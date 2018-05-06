package uniqtext;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Uniq {
    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        String inputStr;
        CommandLineArgument arguments = new CommandLineArgument(args);
        if (arguments.getN() < 0) {
            System.out.println("Аргумент для -s не может быть отрицательным");
            return;
        }
        List<String> lines = new ArrayList<String>();
        if (arguments.getFile() != null) {
            try {
                lines = Files.readAllLines(Paths.get(arguments.getFile().getName()), StandardCharsets.UTF_8);
            } catch (Exception e) {
                System.out.println("Имя входного файла введено некорректно");
                return;
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
        text.makeTextUniq(arguments);
        if (arguments.getOFile() != null) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(arguments.getOFile()), "utf-8"))) {
                for (String line : text.getLines()) {
                    writer.write(line);
                    writer.write("\n");
                }
            } catch (IOException e) {
                System.out.println("Имя выходного фалйа было задано неверно");
            }
        } else {
            for (String line : text.getLines()) {
                System.out.println(line);
            }
        }
    }
}
