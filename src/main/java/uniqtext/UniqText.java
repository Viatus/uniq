package uniqtext;


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
            if (!arguments.getU()) {
                if (arguments.getC()) {
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
                if (nextLine.substring(arguments.getN()).equals(currentLine.substring(arguments.getN()))
                        || (nextLine.substring(arguments.getN()).equalsIgnoreCase(currentLine.substring(arguments.getN())) && arguments.getI())) {
                    currentSequenceLength++;
                } else {
                    updateNewTex(currentSequenceLength, firstSequenceIndex, newText);
                    currentSequenceLength = 1;
                    firstSequenceIndex = j + 1;
                }
            } catch (StringIndexOutOfBoundsException e) {
                if (nextLine.length() < arguments.getN() && currentLine.length() < arguments.getN()) {
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
