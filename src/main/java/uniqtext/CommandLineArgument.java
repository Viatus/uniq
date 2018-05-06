package uniqtext;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

public class CommandLineArgument {
    @Option(name = "-i", usage = "Sets I parameter")
    private boolean isI;

    @Option(name = "-u", usage = "Sets U parameter")
    private boolean isU;

    @Option(name = "-c", usage = "Sets C parameter", forbids = {"-u"})
    private boolean isC;

    @Option(name = "-s", usage = "Sets S parameter")
    private int N;

    @Option(name = "-o", usage = "Sets file name")
    private File oFile;

    @Argument()
    private File file;

    public boolean getU() {
        return isU;
    }

    public boolean getC() {
        return isC;
    }

    public int getN() {
        return N;
    }

    public boolean getI() {
        return isI;
    }

    public File getFile() {
        return file;
    }

    public File getOFile() {
        return oFile;
    }

    public CommandLineArgument(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

}
