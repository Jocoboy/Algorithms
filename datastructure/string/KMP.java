package datastructure.string;

/**
 * Implementation of Knuth–Morris–Pratt algorithm.
 */

public class KMP {

    /**
     * Compute the length of the longest common prefix and suffix before index (i+1).
     * Restored in array next.
     * 
     * @param subString - the needle
     * @return array next that records LCP
     */
    private static int[] nextOf(final String subString) {
        final int n = subString.length();
        final int[] next = new int[n];
        next[0] = 0;
        int j = 0;
        for (int i = 1; i < n; i++) {
            while (j > 0 && subString.charAt(j) != subString.charAt(i)) {
                j = next[j - 1];
            }
            if (subString.charAt(j) == subString.charAt(i)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * Print the pattern starting index in order.
     * 
     * @param haystack - main string
     * @param needle - pattern string
     */
    public static void patternMatch(final String haystack, final String needle) {
        final int[] next = nextOf(needle);
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            if (j == needle.length()) {
                System.out.println("Matched at index : " + (i - (needle.length() - 1)));
                j = next[j - 1];
            }
        }
    }

    public static void main(String[] args) {
        final String haystack1 = "I might say that success is won by three things: first, effort; second, more effort; third, still more effort.";
        final String needle1 = "effort";
        patternMatch(haystack1, needle1);
        System.out.println();
        final String haystack2 = "可以说成功要靠三件事才能赢得：努力，努力，再努力。";
        final String needle2 = "努力";
        patternMatch(haystack2, needle2);
        System.out.println();
    }
}