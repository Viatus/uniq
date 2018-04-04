package uniqText;


import java.util.ArrayList;
import java.util.List;

public class uniqText {
    private boolean isI = false;

    private int N = 0;

    private boolean isU = false;

    private boolean isC = false;

    private List<String> text;

    public uniqText(List<String> text) {
        this.text = text;
    }

    public List<String> getArray() {
        return text;
    }

    private void setProperties(String params) {
        if (params.isEmpty()) {
            return;
        }
        String[] parts = params.split(" ");
        for (int j = 0; j < parts.length; j++) {
            boolean isCommandsUsed = false;
            if (parts[j].equals("-i")) {
                if (!isI) {
                    isI = true;
                    isCommandsUsed = true;
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (parts[j].equals("-u")) {
                if (!isU) {
                    isU = true;
                    isCommandsUsed = true;
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (parts[j].equals("-c")) {
                if (!isC) {
                    isC = true;
                    isCommandsUsed = true;
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (parts[j].equals("-s")) {
                isCommandsUsed = true;
            }
            if (!isCommandsUsed) {
                try {
                    if (parts[j - 1].equals("-s")) {
                        try {
                            N = Integer.parseInt(parts[j]);
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException();
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                } catch (IndexOutOfBoundsException e) {
                    throw new IllegalArgumentException();
                }
            }
        }
    }


    public void mkUniqText(String params) {
        setProperties(params);
        ArrayList<String> newText = new ArrayList<String>();
        Integer currentSequenceLength = 1;
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
                if (currentSequenceLength != 1) {
                    if (!isU) {
                        if (isC) {
                            newText.add(currentSequenceLength.toString() + text.get(j));
                        } else {
                            newText.add(text.get(j));
                        }
                    }
                } else {
                    newText.add(text.get(j));
                }
                currentSequenceLength = 1;
            }
        }
        if (currentSequenceLength != 1) {
            if (!isU) {
                if (isC) {
                    newText.add(currentSequenceLength.toString() + text.get(text.size() - 1));
                } else {
                    newText.add(text.get(text.size() - 1));
                }
            }
        } else {
            newText.add(text.get(text.size() - 1));
        }
        text = newText;
    }

}
