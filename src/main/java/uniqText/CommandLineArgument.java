package uniqText;

import com.sun.org.apache.xpath.internal.Arg;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

public class CommandLineArgument {
    @Option(name = "-i", usage = "Sets I parameter")
    public boolean isI;

    @Option(name = "-u", usage = "Sets U parameter")
    public boolean isU;

    @Option(name = "-c", usage = "Sets C parameter", forbids={"-u"})
    public boolean isC;

    @Option(name = "-s", usage = "Sets S parameter")
    public int N;

    @Option(name = "-o", usage = "Sets file name")
    public File oFile;

    @Argument()
    public File file;

    public CommandLineArgument(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

}
