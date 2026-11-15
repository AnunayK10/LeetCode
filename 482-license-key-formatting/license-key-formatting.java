class Solution {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        // Iterate backwards from the end of the string
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            
            if (c == '-') {
                continue;
            }

            // If we've already added k characters, insert a dash
            if (count > 0 && count % k == 0) {
                sb.append('-');
            }

            sb.append(Character.toUpperCase(c));
            count++;
        }

        // Since we built the string backwards, reverse it to get the final result
        return sb.reverse().toString();
    }
}