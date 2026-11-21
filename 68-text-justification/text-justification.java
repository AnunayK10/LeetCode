class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int n = words.length;
        int i = 0;

        while (i < n) {
            int j = i + 1;
            int lineChars = words[i].length();

            // Greedily include words while minimum required spaces fit:
            // between (j - i) words we need at least (j - i - 1) spaces
            while (j < n && lineChars + words[j].length() + (j - i) <= maxWidth) {
                lineChars += words[j].length();
                j++;
            }

            int numWords = j - i;
            int totalSpaces = maxWidth - lineChars;
            StringBuilder line = new StringBuilder(maxWidth);

            // Case 1: Last line OR single word on the line -> left-justified
            if (j == n || numWords == 1) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        line.append(' ');
                        totalSpaces--;
                    }
                }
                while (totalSpaces > 0) {
                    line.append(' ');
                    totalSpaces--;
                }
            } else {
                // Case 2: Multiple words on a non-last line -> distribute evenly,
                // giving extra remainder spaces to the leftmost gaps
                int gaps = numWords - 1;
                int baseSpaces = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        int spacesToAdd = baseSpaces + (extraSpaces > 0 ? 1 : 0);
                        if (extraSpaces > 0) {
                            extraSpaces--;
                        }
                        for (int s = 0; s < spacesToAdd; s++) {
                            line.append(' ');
                        }
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}