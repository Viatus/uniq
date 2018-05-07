package uniqtext;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

/**
 * Класс - модель аргументов командной строки
 *
 * @author Калашников Роман
 */
public class CommandLineArgument {
    /**
     * Поле игнорирования регистра при сравнении строк
     */
    @Option(name = "-i", usage = "Sets I parameter")
    private boolean isI;

    /**
     * Поле удаления всех одинаковых строк
     */
    @Option(name = "-u", usage = "Sets U parameter")
    private boolean isU;

    /**
     * Поле добавления количества одинаковых строк в начало оставшейся
     */
    @Option(name = "-c", usage = "Sets C parameter", forbids = {"-u"})
    private boolean isC;

    /**
     * Поле количества перекрываемых символов
     */
    @Option(name = "-s", usage = "Sets S parameter")
    private int N;

    /**
     * Поле выходного файла
     */
    @Option(name = "-o", usage = "Sets file name")
    private File oFile;

    /**
     * Поле имени входного файла
     */
    @Argument()
    private File file;

    /**
     * Get-функция для флага -u
     *
     * @return - значение -u
     */
    public boolean getU() {
        return isU;
    }

    /**
     * Get-функция для флага -с
     *
     * @return - значение -с
     */
    public boolean getC() {
        return isC;
    }

    /**
     * Get-функция для значения флага -ы
     *
     * @return - значение N
     */
    public int getN() {
        return N;
    }

    /**
     * Get-функция для флага -i
     *
     * @return - значение -i
     */
    public boolean getI() {
        return isI;
    }

    /**
     * Get-функция для входного файла
     *
     * @return - входной файл
     */
    public File getFile() {
        return file;
    }

    /**
     * Get-функция для выходного файла
     *
     * @return - выходной файл
     */
    public File getOFile() {
        return oFile;
    }

    /**
     * Конструктор - создает CommandLineArgument по массиву строк-аргументов
     *
     * @param args - массив строк-аругментов
     * @throws IllegalArgumentException - выкидывается при невозможности разбить строку или в случае, когда N<0
     */
    public CommandLineArgument(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException();
        }
        if (N < 0) {
            System.out.println("Аргумент для -s не может быть отрицательным");
            throw new IllegalArgumentException();
        }
    }

}
