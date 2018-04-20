package uniqText;


import java.util.ArrayList;
import java.util.List;

public class UniqText {
    private boolean isI = false;

    private int N = 0;

    private boolean isU = false;

    private boolean isC = false;

    private List<String> text;

    public UniqText(List<String> text) {
        this.text = text;
    }

    public List<String> getArray() {
        return text;
    }

    private void updatedNewTex(Integer currentSequenceLength, int firstSequenceIndex, ArrayList<String> newText) {
        if (currentSequenceLength != 1) {
            if (!isU) {
                if (isC) {
                    newText.add(currentSequenceLength.toString() + text.get(firstSequenceIndex));
                } else {
                    newText.add(text.get(firstSequenceIndex));
                }
            }
        } else {
            newText.add(text.get(firstSequenceIndex));
        }
    }

    public void makeTextUniq(boolean iFlag, boolean uFlag, boolean cFlag, int sNumbers) {
        isI = iFlag;
        isU = uFlag;
        isC = cFlag;
        N = sNumbers;
        ArrayList<String> newText = new ArrayList<String>();
        Integer currentSequenceLength = 1;
        int firstSequenceIndex = 0;
        for (int j = 0; j < text.size() - 1; j++) {
            String nextLine = text.get(j + 1);
            String currentLine = text.get(j);
            if (N > nextLine.length() || N > currentLine.length()) {
                throw new IllegalArgumentException();
            }
            nextLine = nextLine.substring(N);
            currentLine = currentLine.substring(N);
            if (nextLine.equals(currentLine) || (nextLine.equalsIgnoreCase(currentLine) && isI)) {
                currentSequenceLength++;
            } else {
                updatedNewTex(currentSequenceLength, firstSequenceIndex, newText);
                currentSequenceLength = 1;
                firstSequenceIndex = j + 1;
            }
        }
        updatedNewTex(currentSequenceLength, firstSequenceIndex, newText);
        text = newText;
    }

}
