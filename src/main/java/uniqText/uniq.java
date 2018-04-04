package uniqText;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class uniq {
    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        String inputStr = str.nextLine();
        String[] parts = inputStr.split(" ");
        if (!parts[0].equals("uniq")) {
            System.out.println("Uniq не была вызвана");
            return;
        }
        File input = new File("");
        String output = "";
        StringBuilder params = new StringBuilder("");
        for (int j = 1; j < parts.length; j++) {
            if (!parts[j].equals("-i") && !parts[j].equals("-c") && !parts[j].equals("-u") && !parts[j].equals("-s")
                    && !parts[j - 1].equals("-s")) {
                if (parts[j].equals("-o")) {
                    output = parts[j + 1];
                } else {
                    input = new File(parts[j]);
                }
            } else {
                params.append(" ").append(parts[j]);
            }
        }
        List<String> lines = new ArrayList<String>();
        if (input.getPath().equals("")) {
            System.out.println("Вводите текст, для того чтобы прекратить ввод, введите end");
            inputStr = str.nextLine();
            while (!inputStr.equalsIgnoreCase("end")) {
                lines.add(inputStr);
                inputStr = str.nextLine();
            }
        } else {
            try {
                BufferedReader in = new BufferedReader(new FileReader(input));
                try {
                    String s;
                    while ((s = in.readLine()) != null) {
                        lines.add(s);
                    }
                } finally {
                    in.close();
                }
            } catch (Exception e) {
                System.out.println("Название входного файла введено неверно");
                return;
            }
        }
        uniqText text = new uniqText(lines);
        try {
            text.mkUniqText(params.toString().trim());
        } catch (IllegalArgumentException e) {
            System.out.println("Параметры были введены неверно");
            return;
        }
        if (!output.equals("")) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(output), "utf-8"))) {
                for (String line : text.getArray()) {
                    writer.write(line);
                    writer.write("\n");
                }
            } catch (IOException e) {
            }
        } else {
            for (String line : text.getArray()) {
                System.out.println(line);
            }
        }
    }
}
