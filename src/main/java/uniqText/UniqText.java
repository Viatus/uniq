package uniqText;


import java.util.ArrayList;
import java.util.List;

public class UniqText {

    private CommandLineArgument arguments;

    private List<String> text;

    public UniqText(List<String> text) {
        this.text = text;
    }

    public List<String> getLines() {
        return text;
    }

    private void updateNewTex(Integer currentSequenceLength, int firstSequenceIndex, ArrayList<String> newText) {
        if (currentSequenceLength != 1) {
            if (!arguments.isU) {
                if (arguments.isC) {
                    newText.add(currentSequenceLength.toString() + text.get(firstSequenceIndex));
                } else {
                    newText.add(text.get(firstSequenceIndex));
                }
            }
        } else {
            newText.add(text.get(firstSequenceIndex));
        }
    }

    public void makeTextUniq(CommandLineArgument args) {
        arguments = args;
        ArrayList<String> newText = new ArrayList<String>();
        Integer currentSequenceLength = 1;
        int firstSequenceIndex = 0;
        for (int j = 0; j < text.size() - 1; j++) {
            String nextLine = text.get(j + 1);
            String currentLine = text.get(j);
            try {
                if (nextLine.substring(arguments.N).equals(currentLine.substring(arguments.N))
                        || (nextLine.substring(arguments.N).equalsIgnoreCase(currentLine.substring(arguments.N)) && arguments.isI)) {
                    currentSequenceLength++;
                } else {
                    updateNewTex(currentSequenceLength, firstSequenceIndex, newText);
                    currentSequenceLength = 1;
                    firstSequenceIndex = j + 1;
                }
            } catch (StringIndexOutOfBoundsException e) {
                if (nextLine.length() < arguments.N && currentLine.length() < arguments.N) {
                    currentSequenceLength++;
                } else {
                    updateNewTex(currentSequenceLength, firstSequenceIndex, newText);
                    currentSequenceLength = 1;
                    firstSequenceIndex = j + 1;
                }
            }
        }
        updateNewTex(currentSequenceLength, firstSequenceIndex, newText);
        text = newText;
    }
}
